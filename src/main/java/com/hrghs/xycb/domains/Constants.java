package com.hrghs.xycb.domains;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
    public static final String DB_HIKARI_MAX_SIZE = "spring.datasource.banmaerp.hikari.maximum-pool-size";
    public static final String DB_HIKARI_MAX_LIFE = "spring.datasource.banmaerp.hikari.max-lifetime";
    public static final String DB_HIKARI_MIN_IDLE = "spring.datasource.banmaerp.hikari.minimum-idle";
    public static final String DB_HIKARI_IDLE_TIMEOUT = "spring.datasource.banmaerp.hikari.idle-timeout";
    public static final String DB_HIKARI_CONNECT_TIMEOUT = "spring.datasource.banmaerp.hikari.connection-timeout";
    public static final String DB_HIKARI_KEEPALIVETIME = "spring.datasource.banmaerp.hikari.keepaliveTime";
    public static final String DB_HIKARI_JDBC_URL = "spring.datasource.banmaerp.hikari.jdbc-url";
    public static final Long DB_HIKARI_MINIMUM_KEEPALIVETIME = 30000l;
    public static final Long DB_HIKARI_DEFAULT_KEEPALIVETIME = 60000l;
    public static final Integer PAGE_SIZE_DEFAULT = 50;

    public static final String DB_JDBC_PROPERTY_CACHEPREPARESTATEMENTS = "jdbc.property.cachePrepStmts";
    public static final String DB_JDBC_PROPERTY_PREPARESTATEMENT_CACHESIZE = "jdbc.property.prepStmtCacheSize";
    public static final String DB_JDBC_PROPERTY_PREPARESTATEMENT_CACHESQLLIMIT = "jdbc.property.prepStmtCacheSqlLimit";
    public static final String DB_JDBC_DRIVER_H2DB = "org.h2.Driver";
    public static final String DB_JDBC_URL_HIKARI_H2DB = "jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_ON_EXIT=TRUE;INIT=runscript from 'classpath:init-script.sql'";
    public static final String DB_JDBC_DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";

    public static final String DB_BANMAERP_USERNAME = "spring.datasource.banmaerp.username";
    public static final String DB_BANMAERP_PASSWORD = "spring.datasource.banmaerp.password";
    public static final String DB_BANMAERP_URL = "spring.datasource.banmaerp.url";

    public static final String DB_JDBC_TEST_QUERY = "SELECT 1";
    public static final String ENV_PROFILES_H2DB = "h2db";
    public static final String ENV_PROFILES_DEV = "dev";
    public static final String ENTITY_PACKGES_XYHZSTORE = "com.hrghs.xycb.domains.xyhzstore";
    public static final String ENTITY_PACKGES_BANMAERP = "com.hrghs.xycb.domains";
    public static final String JPA_PROS_HIBERNATE_TRANSACTIONMANAGER_LOOKUP_CLASS = "hibernate.transaction.manager_lookup_class";// @deprecated since hibernate 3
    public static final String JPA_PROS_HIBERNATE_TRANSACTIONMANAGER_COORDINATE_CLASS = "hibernate.transaction.coordinator_class";
    public static final String JPA_PROS_HIBERNATE_ALLOW_UPDATE_OUTSIDE_TRANSACATION = "hibernate.allow_update_outside_transaction";
    public static final String JPA_PROS_HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String JPA_PROS_HIBERNATE_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    public static final String JPA_PROS_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String JPA_PROS_HIBERNATE_ENABLE_LAZYLOAD_NOTRANS = "hibernate.enable_lazy_load_no_trans";
    public static final String JPA_PROS_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String JPA_PROS_TRANSACTION_TYPE = "javax.persistence.transactionType";

//    spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
//    spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
//    spring.jpa.hibernate.ddl-auto=update
//    spring.jpa.generate-ddl=true
    public static final String Data ="Data";
    public static final String BANMAERP_FIELD_DATAACCESS ="DataAccess";
    public static final String BANMAERP_FIELD_STORES = "Stores";
    public static final String BANMAERP_FIELD_ACCOUNTS = "Accounts";
    public static final String BANMAERP_FIELD_CATEGORYS = "Categorys";
    public static final String BANMAERP_FIELD_ORDERS = "Orders";
    public static final String BANMAERP_FIELD_PRODUCTS = "Products";
    public static final String BANMAERP_FIELD_SKUS = "SKUs";
    public static final String BANMAERP_FIELD_TAGS = "Tags";
    public static final String BANMAERP_FIELD_SUPPLIERS = "Suppliers";
    public static final String BANMAERP_FIELD_STORAGES = "Storages";
    public static final String BANMAERP_FIELD_PAGE = "Page";
    public static final String BANMAERP_FIELD_HasMore = "HasMore";
    public static final String BANMAERP_FIELD_PAGE_TotalCount = "TotalCount";//数据总量
    public static final String BANMAERP_FIELD_PAGE_PageSize = "PageSize";
    public static final String BANMAERP_FIELD_PAGE_PageCount = "PageCount";//总页数
    public static final String BANMAERP_FIELD_PAGE_PageNumber = "PageNumber";
    public static final String BANMAERP_FIELD_PRODUCT = "Product";
    public static final String BANMAERP_FIELD_FULFILLMENTS = "Fulfillments";
    public static final String BANMAERP_FIELD_TRACKINGS = "Trackings";
    public static final String BANMAERP_FIELD_PREFIX = "banmaerp";
    public static final String BANMAERP_FIELD_TOKEN = "token";
    public static final String BANMAERP_FIELD_APPINFO = "appinfo";

    public static final String BANMAERP_FIELD_SSOTOKEN = "ssotoken";
    public static final String BANMAERP_FIELD_TASK = "task";
    public static final String BANMAERP_FIELD_CLIENTIP = "clientIp";
    public static final String BANMAERP_FIELD_APPID = "app_id";
    public static final String BANMAERP_FIELD_APPSECRET = "app_secret";
    public static final String BANMAERP_MESSAGE_UNKNOWNERROR = "Unknown Error!";
    public static final String BANMAERP_MESSAGE_ILLEGAL_ARGS = "Illegal Arguments!";
    public static final String BANMAERP_MESSAGE_TOKEN_EXPIRED = "ACCESS_TOKEN Has Expired!";
    public static final String BANMAERP_MESSAGE_ILLEGAL_ARGS_BanmaerpProperties = "BanmaerpProperties can not be null!";


    public static final String BANMAERP_MESSAGE_REGISTER_ERROR = "账号注册失败!";
    public static final String BANMAERP_MESSAGE_CONFIGURATION_ERROR = "erp.banmaerp.BanmaerpProperties not config properly!";

    public static final Set<String> IpAquireUrls = Stream.of("http://checkip.amazonaws.com/"
            ,"https://ipv4.icanhazip.com/","http://myexternalip.com/raw","http://ipecho.net/plain")
            .collect(Collectors.toSet());

    public static final String DATETIME_FORMAT_DASH_SHORT ="yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_SLASH_SHORT ="MM/dd/yyyy HH:mm:ss";


    public static final String WEBHOOK_BASEURL_WECHAT_ENTERPISE= "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s";
    public static final String WEBHOOK_BASEURL_DINGTALK_ENTERPISE= "https://oapi.dingtalk.com/robot/send?access_token=%s";

}
