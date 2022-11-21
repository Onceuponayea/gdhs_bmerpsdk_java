package com.hrghs.xycb.config;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.ReactiveRedisLockProvider;
import org.h2.tools.Server;
import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;

import static com.hrghs.xycb.domains.Constants.*;

@Configuration
public class DbConfigs {
    public static Server h2DBServer;
    @Autowired
    private Environment env;
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;
    @Bean
    @ConditionalOnMissingBean
    public ReactiveRedisConnectionFactory redisConnectionFactory(@Value("${spring.redis.host:localhost}") String host,
                                                                 @Value("${spring.redis.port:3306}")int port,
                                                                 @Value("${spring.redis.password}")String password,
                                                                 @Value("${spring.redis.database:0}")int db){
        return null;
    }
    public LockProvider lockProvider(ReactiveRedisConnectionFactory connectionFactory){
        return new ReactiveRedisLockProvider.Builder(connectionFactory).build();
    }

    @Primary
    @Bean(name = "dataSourceXABanmaerp")
    public DataSource dataSourceXABanmaerp(BanmaerpDruidXADataSource banmaerpDruidXADataSource) throws SQLException {
        AtomikosDataSourceBean ds = atomikosDataSourceBean(banmaerpDruidXADataSource);
        ds.setUniqueResourceName("dataSourceXABanmaerp");
        return ds;
    }
    @Bean(name = "dataSourceBanmaerp")
    public DataSource dataSourceBanmaerp(){
        HikariConfig hikariConfig = hikariConfig();
        boolean useH2DB = Arrays.stream(env.getActiveProfiles())
                .anyMatch(s -> s.toLowerCase(Locale.ROOT).equalsIgnoreCase(ENV_PROFILES_H2DB));
        String driverClass = useH2DB ?DB_JDBC_DRIVER_H2DB:DB_JDBC_DRIVER_MYSQL;
        String jdbcurl = useH2DB ?DB_JDBC_URL_HIKARI_H2DB:env.getProperty(DB_HIKARI_JDBC_URL);
        hikariConfig.setDriverClassName(driverClass);
        hikariConfig.setJdbcUrl(jdbcurl);
        if (!useH2DB){
            hikariConfig.setUsername(env.getProperty(DB_BANMAERP_USERNAME));
            hikariConfig.setPassword(env.getProperty(DB_BANMAERP_PASSWORD));
        }
        return  new HikariDataSource(hikariConfig);
    }

    /**
     * init size equals druid configurations
     * @return
     * @throws SQLException
     */
    @Bean
    @ConditionalOnMissingBean
    public AtomikosDataSourceBean atomikosDataSourceBean(BanmaerpDruidXADataSource banmaerpDruidXADataSource) throws SQLException {
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setMaxPoolSize(banmaerpDruidXADataSource.getMaxActive());
        dataSourceBean.setMinPoolSize(banmaerpDruidXADataSource.getMinIdle());
        /**
         * atomikos代理druid数据源：
         * druid数据源有自己的线程管理连接池的销毁和声明，atomikos的线程lifeTime不能超过druid的最小空闲时间+检测时间
         */
        int maxIdleTime = (int)((banmaerpDruidXADataSource.getTimeBetweenEvictionRunsMillis()+ banmaerpDruidXADataSource.getMinEvictableIdleTimeMillis())/1000l);
        int maxLifeTime  = (int)(banmaerpDruidXADataSource.getMaxEvictableIdleTimeMillis()/1000l);
        dataSourceBean.setMaxIdleTime(maxIdleTime);
        dataSourceBean.setMaxLifetime(maxLifeTime);
        dataSourceBean.setMaintenanceInterval((int)(banmaerpDruidXADataSource.getKeepAliveBetweenTimeMillis()/1000l));
        dataSourceBean.setBorrowConnectionTimeout((int)(banmaerpDruidXADataSource.getMaxWait()/1000l));
        dataSourceBean.setLoginTimeout(banmaerpDruidXADataSource.getLoginTimeout());
        /**
         * reapTimeout 是管理 Connection 被占用的时间
         */
        dataSourceBean.setReapTimeout(maxLifeTime-maxIdleTime);
        dataSourceBean.setTestQuery(DB_JDBC_TEST_QUERY);
        dataSourceBean.setXaDataSource(banmaerpDruidXADataSource);
        return dataSourceBean;
    }
    private HikariConfig hikariConfig(){
        HikariConfig hikariConfig = new HikariConfig();
        Long hikariMaxLife = Long.parseLong(env.getProperty(DB_HIKARI_MAX_LIFE));
        Long hikariKeepAlive=Long.parseLong(env.getProperty(DB_HIKARI_KEEPALIVETIME));
        hikariKeepAlive = (hikariKeepAlive<DB_HIKARI_MINIMUM_KEEPALIVETIME||hikariKeepAlive>=hikariMaxLife)?DB_HIKARI_DEFAULT_KEEPALIVETIME:hikariKeepAlive;

        hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty(DB_HIKARI_MAX_SIZE)));
        hikariConfig.setMinimumIdle(Integer.parseInt(env.getProperty(DB_HIKARI_MIN_IDLE)));
        hikariConfig.setMaxLifetime(Long.parseLong(env.getProperty(DB_HIKARI_MAX_LIFE)));
        hikariConfig.setConnectionTimeout(Long.parseLong(env.getProperty(DB_HIKARI_CONNECT_TIMEOUT)));
        hikariConfig.setIdleTimeout(Long.parseLong(env.getProperty(DB_HIKARI_IDLE_TIMEOUT)));
        hikariConfig.setKeepaliveTime(hikariKeepAlive);
        hikariConfig.setScheduledExecutor(scheduledExecutorService);
        return hikariConfig;
    }
    @Bean(name = "banmaerpEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory(AtomikosDataSourceBean dataSourceBean){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setDataSource(dataSourceBean);
        //factoryBean.setPackagesToScan(ENTITY_PACKGES_XYHZSTORE);
        factoryBean.setPackagesToScan(ENTITY_PACKGES_BANMAERP);
        Properties jpaPros = new Properties();
        jpaPros.put(JPA_PROS_HIBERNATE_SHOW_SQL,true);
        jpaPros.put(JPA_PROS_HIBERNATE_FORMAT_SQL,true);
        jpaPros.put(JPA_PROS_TRANSACTION_TYPE,"jta");
        jpaPros.put(JPA_PROS_HIBERNATE_SESSION_CONTEXT_CLASS,"jta");
        jpaPros.put(JPA_PROS_HIBERNATE_DIALECT, MySQL8Dialect.class.getName());
        jpaPros.put(JPA_PROS_HIBERNATE_TRANSACTIONMANAGER_COORDINATE_CLASS,"jdbc");//full name: org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl
        //jpaPros.put(JPA_PROS_HIBERNATE_TRANSACTIONMANAGER_LOOKUP_CLASS,"com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup");
        factoryBean.setJpaProperties(jpaPros);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    /**
     * @@see http://www.javacui.com/opensource/583.html
     * @param timeoutMs
     * @return
     * @throws SystemException
     */
    @Bean(name = "banmaerpXATransactionManager")
    @ConditionalOnMissingBean
    public PlatformTransactionManager jtaTransactionManager(@Value("${spring.jta.atomikos.transaction.timeout:30000}")int timeoutMs) throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(timeoutMs);
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager);
        jtaTransactionManager.setUserTransaction(userTransaction);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        //jtaTransactionManager.setNestedTransactionAllowed(true);
        return jtaTransactionManager;
    }

    @Bean
    @ConditionalOnProperty(prefix = "h2server", name = "enabled",havingValue = "true")
    public Server h2Server(@Value("${h2server.port:7777}")String h2DBPort) throws SQLException {
        this.h2DBServer = Server.createPgServer("-web","-webAllowOthers","-webPort",h2DBPort).start();
        return this.h2DBServer;
    }

    @PreDestroy
    public void shutdownH2DB(){
        this.h2DBServer.shutdown();
        //todo kill whatever process that possess port 7777
    }
}