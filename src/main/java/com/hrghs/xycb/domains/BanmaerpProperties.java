package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "bmerp_properties")
@Table(name = "bmerp_properties")
@Component
@ConstructorBinding
@Data
@ConfigurationProperties(prefix = "erp.banmaerp",ignoreInvalidFields=true)
@EnableJpaRepositories(entityManagerFactoryRef="banmaerpEntityManagerFactory",transactionManagerRef="banmaerpXATransactionManager",
        bootstrapMode= BootstrapMode.DEFAULT,basePackages = "com.hrghs.xycb.repositories")
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
    @Id
    @Column(name = "id")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID bmerp_id;
    @Column
    private  String X_BANMA_APP_NAME;
    @Column
    private  String X_BANMA_MASTER_APP_ID;
    @Column
    private  String X_BANMA_MASTER_APP_SECRET;
    @Column
    private  String X_BANMA_MASTER_APP_ACCOUNT;
    @Column
    private  String X_BANMA_MASTER_SIGN_METHOD;
    @Column
    private  String X_BANMA_MASTER_SIGN_ALGORITHM;

    /**
     * ssoToken的有效期，斑马目前还在开发，口头称有20秒的有效期，后期可能会更改，不宜写死
     */
    @JsonIgnore
    @Transient
    private  Long X_BANMA_TOKEN_SSOTOKEN_TTL = 20000l;

    /**
     * dynamic added in runtime. must persist to db
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "bmerp_account")
    private List<AccountDTO> banmaErpAccounts;


}
