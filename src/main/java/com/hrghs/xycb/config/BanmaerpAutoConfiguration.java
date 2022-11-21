package com.hrghs.xycb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.ReactiveRedisLockProvider;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.hrghs.xycb.domains.Constants.*;

@Configuration
@ComponentScan
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ConditionalOnProperty(value = "enabled",prefix = "erp.banmaerp", havingValue = "true", matchIfMissing = false)
public class BanmaerpAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true)
                .registerModule(new JodaModule());
    }
    @Bean
//    @ConditionalOnMissingBean
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
    public ScheduledExecutorService taskScheduler(@Value("${thread.async.pool.maxPoolSize:200}") int maxSize,
                                                  @Value("${thread.async.pool.corePoolSize:50}")int coreSize,
                                                  @Value("${thread.async.pool.keepAliveSeconds:60}")long keepalive){
        ScheduledThreadPoolExecutor executor=  new ScheduledThreadPoolExecutor(coreSize);
        executor.setRemoveOnCancelPolicy(true);
        executor.setMaximumPoolSize(maxSize);
        executor.setKeepAliveTime(keepalive, TimeUnit.SECONDS);
        return executor;
    }

}
