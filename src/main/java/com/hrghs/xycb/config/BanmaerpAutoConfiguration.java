package com.hrghs.xycb.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.services.*;
import com.hrghs.xycb.services.impl.*;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.utils.WebHookUtils;
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
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;
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
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(jodaModule());
        return objectMapper;
    }
    @Primary
    @Bean
    public JodaModule jodaModule(){
        JodaModule jodaModule = new JodaModule();
        jodaModule.addDeserializer(DateTime.class, new JodaDateTimeDeserialiser());
        jodaModule.addSerializer(DateTime.class,new JodaDateTimeSerialiser());
        return jodaModule;
    }
    @Primary
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer(){
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.configure(objectMapper());
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
    public RestTemplate restTemplate(){
        return new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
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
    @DependsOn(value = {"restTemplate"})
    public BanmaTokenUtils banmaTokenUtils(){
        return new BanmaTokenUtils();
    }
    @Bean
//    @Lazy
    public AccountService accountService(){
        return new AccountServiceImpl();
    }
    @Bean
//    @Lazy
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }
    @Bean
//    @Lazy
    public OrderService orderService(){
        return new OrderServiceImpl();
    }
    @Bean
//    @Lazy
    public ProductService productService(){
        return new ProductServiceImpl();
    }
    @Bean
//    @Lazy
    public StoreService storeService(){
        return new StoreServiceImpl();
    }
    @Bean
//    @Lazy
    public StorageService storageService(){
        return new StorageServiceImpl();
    }
    @Bean
//    @Lazy
    public SsoService ssoService(){
        return new SsoServiceImpl();
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
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        logger.info("Banmaerp module integrating successfully!");

    }
}
