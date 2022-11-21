package com.hrghs.xycb.config;


import com.hrghs.xycb.domains.BanmaErpAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConstructorBinding
@Data
@ConfigurationProperties(prefix = "erp.banmaerp",ignoreInvalidFields=true)
@EnableJpaRepositories(entityManagerFactoryRef="banmaerpEntityManagerFactory",transactionManagerRef="banmaerpXATransactionManager",
        bootstrapMode= BootstrapMode.LAZY,basePackages = "com.hrghs.xycb.repositories")
public class BanmaerpProperties {
    /**
     * @@apiNote Immutable property for banmaERP api mandatory Headers
     */
    public final static String BANMA_HEADER_APPID = "X-BANMA-APP-ID";
    public final static String BANMA_HEADER_TIMESTAMP = "X-BANMA-TIMESTAMP";
    public final static String BANMA_HEADER_SIGN = "X-BANMA-SIGN";
    public final static String BANMA_HEADER_SIGNMETHOD = "X-BANMA-SIGN-METHOD";
    public final static String BANMA_HEADER_ACCESSTOKEN = "X-BANMA-ACCESS-TOKEN";

    /**
     * @@see https://open.banmaerp.com/Docs/3.Authorize.md
     * @@apiNote property from banmaERP for signning purpose.
     * sign(app_id, app_secret, timestamp, request_method, request_path, request_query, request_body);
     */
    private  String X_BANMA_APP_NAME;
    private  String X_BANMA_MASTER_APP_ID;
    private  String X_BANMA_MASTER_APP_SECRET;
    private  String X_BANMA_MASTER_APP_ACCOUNT;
    private  String X_BANMA_MASTER_SIGN_METHOD;
    private  String X_BANMA_MASTER_SIGN_ALGORITHM;

    /**
     * dynamic added in runtime. must persist to db
     */
    private List<BanmaErpAccount> banmaErpAccounts;


}
