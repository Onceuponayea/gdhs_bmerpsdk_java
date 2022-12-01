package com.hrghs.xycb.domains;

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
    public static final String JPA_PROS_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String JPA_PROS_TRANSACTION_TYPE = "javax.persistence.transactionType";


    public static final String Data ="Data";
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
    public static final String BANMAERP_FIELD_FULFILLMENTS = "Fulfillments";
    public static final String BANMAERP_FIELD_TRACKINGS = "Trackings";
    public static final String DATETIME_FORMAT_DASH_SHORT ="yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_SLASH_SHORT ="MM/dd/yyyy HH:mm:ss";

}
