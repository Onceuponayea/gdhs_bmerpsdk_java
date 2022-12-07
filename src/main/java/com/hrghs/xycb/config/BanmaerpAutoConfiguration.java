package com.hrghs.xycb.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.services.*;
import com.hrghs.xycb.services.impl.*;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.utils.WebHookUtils;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, JmsAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
@ComponentScan(basePackages = {"com.hrghs.xycb"})
@EnableConfigurationProperties(value = {BanmaerpProperties.class,BanmaerpDruidXADataSource.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@EnableAsync
@EnableSchedulerLock(defaultLockAtLeastFor = "PT30S",defaultLockAtMostFor = "PT300S")
@ConditionalOnProperty(value = "enabled",prefix = "erp.banmaerp", havingValue = "true", matchIfMissing = false)
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class BanmaerpAutoConfiguration {
    //@Bean(name = "objectMapper")
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
    public EncryptionUtils encryptionUtils(){
        return new EncryptionUtils();
    }
    @Bean
    @DependsOn(value = {"restTemplate"})
    public BanmaTokenUtils banmaTokenUtils(){
        return new BanmaTokenUtils();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public AccountService accountService(){
        return new AccountServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public OrderService orderService(){
        return new OrderServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public ProductService productService(){
        return new ProductServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public StoreService storeService(){
        return new StoreServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public StorageService storageService(){
        return new StorageServiceImpl();
    }
    @Bean
    @Lazy
    @Transactional(transactionManager = "banmaerpXATransactionManager")
    public SsoService ssoService(){
        return new SsoServiceImpl();
    }


}
