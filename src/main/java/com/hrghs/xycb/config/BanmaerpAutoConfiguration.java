package com.hrghs.xycb.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.aops.RestTemplateInterceptor;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;
import com.hrghs.xycb.services.*;
import com.hrghs.xycb.services.impl.*;
import com.hrghs.xycb.utils.*;
import com.hrghs.xycb.utils.converters.EnumeratorDeserialiser;
import com.hrghs.xycb.utils.converters.EnumeratorSerialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, JmsAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
@ComponentScan(basePackages = {"com.hrghs.xycb"})
@EnableConfigurationProperties(value = {BanmaerpProperties.class,BanmaerpDruidXADataSource.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@EnableRetry(proxyTargetClass = true)
@EnableAsync
@EnableSchedulerLock(defaultLockAtLeastFor = "PT30S",defaultLockAtMostFor = "PT300S")
@AutoConfigureAfter(BanmaerpDbAutoConfiguration.class)
@ConditionalOnProperty(value = "enabled",prefix = "erp.banmaerp", havingValue = "true", matchIfMissing = false)
public class BanmaerpAutoConfiguration implements BeanDefinitionRegistryPostProcessor, BeanFactoryAware {
    private static final Logger logger = LoggerFactory.getLogger(BanmaerpAutoConfiguration.class);

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES,false)
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .registerModule(jodaModule());
        return objectMapper;
    }
    @Primary
    @Bean
    public JodaModule jodaModule(){
        JodaModule jodaModule = new JodaModule();
        jodaModule.addDeserializer(DateTime.class, new JodaDateTimeDeserialiser())
                .addSerializer(DateTime.class,new JodaDateTimeSerialiser())
                .addSerializer(BanmaerpAccountEnums.DataAccessMode.class,new EnumeratorSerialiser.DataAccessSerialiser())
                .addDeserializer(BanmaerpAccountEnums.DataAccessMode.class,new EnumeratorDeserialiser.DataAccessDeSerialiser())
                .addSerializer(BanmaerpOrderEnums.Status.class,new EnumeratorSerialiser.OrderStatusSerialiser())
                .addDeserializer(BanmaerpOrderEnums.Status.class,new EnumeratorDeserialiser.OrderStatusDeserialiser());
        return jodaModule;
    }
    @Primary
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer(ApplicationContext context){
        Jackson2ObjectMapperBuilderCustomizer jbc= jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.applicationContext(context)
                .configure(objectMapper());
        return jbc;
    }
    @Bean
    @ConditionalOnMissingBean
    public ThreadPoolTaskExecutor threadPoolTaskScheduler(@Value("${thread.async.pool.maxPoolSize:200}") int maxSize,
                                                          @Value("${thread.async.pool.corePoolSize:50}")int coreSize,
                                                          @Value("${thread.async.pool.keepAliveSeconds:60}")int keepalive,
                                                          @Value("${thread.async.pool.queueCapacity:512}")int queueSize,
                                                          @Value("${thread.async.pool.daemon:true}")boolean daemon){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(coreSize);
        poolTaskExecutor.setMaxPoolSize(maxSize);
        poolTaskExecutor.setKeepAliveSeconds(keepalive);
        poolTaskExecutor.setQueueCapacity(queueSize);
        poolTaskExecutor.initialize();
        poolTaskExecutor.setDaemon(daemon);
        return poolTaskExecutor;
    }
    @Bean
    @ConditionalOnMissingBean
    public ScheduledExecutorService taskScheduler(@Value("${thread.async.pool.maxPoolSize:200}") int maxSize,
                                                  @Value("${thread.async.pool.corePoolSize:50}")int coreSize,
                                                  @Value("${thread.async.pool.keepAliveSeconds:60}")long keepalive){
        ScheduledThreadPoolExecutor executor=  new ScheduledThreadPoolExecutor(coreSize);
        executor.setRemoveOnCancelPolicy(true);
        executor.setMaximumPoolSize(maxSize);
        executor.setKeepAliveTime(keepalive, TimeUnit.SECONDS);
        return executor;
    }

    @Bean
    @ConditionalOnProperty(prefix = "erp.banmaerp.tasks.sync", value = "enabled" ,havingValue = "true", matchIfMissing = true)
    public ScheduleService scheduleService(){
        return new ScheduleService();
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn({"tokenRespReactiveRedisOperations"})
    public RestTemplate restTemplate(ReactiveRedisOperations<String, TokenResponseDTO> tokenRespReactiveRedisOperations
            , ReactiveRedisOperations<String, BanmaerpProperties> bmerp_props,BanmaTokenUtils banmaTokenUtils){
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        /* circle reference,add interceptors when application is started */
        List<ClientHttpRequestInterceptor> interceptorList = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptorList)){
        interceptorList = new ArrayList<>();
        }
        interceptorList.add(new RestTemplateInterceptor(tokenRespReactiveRedisOperations,bmerp_props,banmaTokenUtils));
        restTemplate.setInterceptors(interceptorList);
        return restTemplate;
    }
    @Bean
    @DependsOn(value = {"restTemplate"})
    public WebHookUtils webHookUtils(){
        return new WebHookUtils();
    }

    @Bean
    @DependsOn(value = {"restTemplate"})
    public HttpClientsUtils httpClientsUtils() {
        return new HttpClientsUtils();
    }

    @Bean
    public BanmaEncryptionUtils encryptionUtils(){
        return new BanmaEncryptionUtils();
    }
    @Bean
    public BanmaTokenUtils banmaTokenUtils(){
        return new BanmaTokenUtils();
    }
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }
    @Bean
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl();
    }
    @Bean
    public ProductService productService(){
        return new ProductServiceImpl();
    }
    @Bean
    public StoreService storeService(){
        return new StoreServiceImpl();
    }
    @Bean
    public StorageService storageService(){
        return new StorageServiceImpl();
    }
    @Bean
    public SsoService ssoService(){
        return new SsoServiceImpl();
    }
    @Bean
    @Lazy
    public BanmaerpPropertiesService banmaerpPropertiesService(){
        return new BanmaerpPropertiesServiceImpl();
    }
    @Bean
    public EventService eventService(){
            return new EventService();
    }

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * to programmatically set bean from other projects as primary, like datasource
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        selectPrimaryDataSource(beanDefinitionRegistry);
        selectPrimaryTransactionManager(beanDefinitionRegistry);
        selectPrimaryRedisConnectionFactory(beanDefinitionRegistry);
        addOtherDSIntoTxManagement(beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //unused
    }
    private void selectPrimaryDataSource(BeanDefinitionRegistry beanDefinitionRegistry){
        String[] beansOfType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors((ListableBeanFactory) beanFactory, DataSource.class);
        Set<String> dsNamesInProject = new LinkedHashSet<>();
        dsNamesInProject.add("dataSourceHikariBanmaerp");
        dsNamesInProject.add("dataSourceAtomikosBanmaerp");
        dsNamesInProject.add("spring.datasource.banmaerp.druid-com.hrghs.xycb.config.BanmaerpDruidXADataSource");
        for (String beanName : beansOfType){
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName);
            if (beanDefinition.isPrimary()){
                //found an existing primary bean of same type
                return ;
            }
            if (!dsNamesInProject.contains(beanName)){//or beanName equal dataSource
                beanDefinition.setPrimary(true);
                //if spring.datasource.host spring.datasource.port不存在则直接从beanDefinition获取
            }
        }
    }
    private void selectPrimaryTransactionManager(BeanDefinitionRegistry beanDefinitionRegistry){
        String[] beanDef = BeanFactoryUtils.beanNamesForTypeIncludingAncestors((ListableBeanFactory) beanFactory,org.springframework.transaction.TransactionManager.class);
        Set<String> txNamesInProject = new LinkedHashSet<>();
        txNamesInProject.add("banmaerpXATransactionManager");
        for (String beanName : beanDef){
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName);
            if (beanDefinition.isPrimary()){
                //found an existing primary bean of same type
                return ;
            }
                //or beanName equal transactionManager
            if (!txNamesInProject.contains(beanName)){
                beanDefinition.setPrimary(true);
            }
        }
    }
    private void selectPrimaryRedisConnectionFactory(BeanDefinitionRegistry beanDefinitionRegistry){
        String[] beanDef = BeanFactoryUtils.beanNamesForTypeIncludingAncestors((ListableBeanFactory) beanFactory, ReactiveRedisConnectionFactory.class);
        Set<String> redisConFacInProject = new LinkedHashSet<>();
        redisConFacInProject.add("reactiveRedisConnectionFactory");
        for (String beanName : beanDef){
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName);
            if (beanDefinition.isPrimary())return;
            if (!redisConFacInProject.contains(beanName)){
                beanDefinition.setPrimary(true);
            }
        }
    }
    private void addOtherDSIntoTxManagement(BeanDefinitionRegistry beanDefinitionRegistry){
        //TODO add parent project's datasource into atomikos Transaction Management
    }
}
