package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

@Entity(name = "bmerp_properties")
@Table(name = "bmerp_properties")
@Component
@ConstructorBinding
@Data
@ConfigurationProperties(prefix = "erp.banmaerp",ignoreInvalidFields=true)
public class BanmaerpProperties {
    /**
     * @@apiNote Immutable property for banmaERP api mandatory Headers
     */
    public final static String BANMA_HEADER_APPID = "X-BANMA-APP-ID";
    public final static String BANMA_HEADER_TIMESTAMP = "X-BANMA-TIMESTAMP";
    public final static String BANMA_HEADER_SIGN = "X-BANMA-SIGN";
    public final static String BANMA_HEADER_SIGNMETHOD = "X-BANMA-SIGN-METHOD";
    public final static String BANMA_HEADER_ACCESSTOKEN = "X-BANMA-ACCESS-TOKEN";

    @Column
    private  String X_BANMA_APP_NAME;
    @Id
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
    @JsonIgnore
    @Column
    private  String X_BANMA_MASTER_APP_STATUS;
    @JsonIgnore
    @Column
    private String X_BANMA_MASTER_APP_SCOPES;
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
    //@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "banma_master_app_id")
    private List<AccountDTO> banmaErpAccounts;

    public String getX_BANMA_MASTER_SIGN_ALGORITHM() {
        X_BANMA_MASTER_SIGN_ALGORITHM = StringUtils.hasText(X_BANMA_MASTER_SIGN_ALGORITHM)?
                X_BANMA_MASTER_SIGN_ALGORITHM:SHA256.split("#")[1];
        return X_BANMA_MASTER_SIGN_ALGORITHM;
    }
    @Override
    public String toString(){
        /** prevent dead loop **/
        return "{\n" +
                "  \"X_BANMA_APP_NAME\": \""+ this.getX_BANMA_APP_NAME()+"\",\n" +
                "  \"X_BANMA_MASTER_APP_ID\": \""+ this.getX_BANMA_MASTER_APP_ID()+"\" ,\n" +
                "  \"X_BANMA_MASTER_APP_SECRET\":\" " +this.getX_BANMA_MASTER_APP_SECRET()+" \",\n" +
                "  \"X_BANMA_MASTER_APP_ACCOUNT\": "+ this.getX_BANMA_MASTER_APP_ACCOUNT()+ " ,\n" +
                "  \"X_BANMA_MASTER_SIGN_METHOD\":\" "+this.getX_BANMA_MASTER_SIGN_METHOD()+" \",\n" +
                "  \"X_BANMA_MASTER_SIGN_ALGORITHM\":\" "+this.getX_BANMA_MASTER_SIGN_ALGORITHM()+" \",\n" +
                "  \"X_BANMA_MASTER_APP_STATUS\":\""+this.getX_BANMA_MASTER_APP_STATUS()+"\",\n" +
                "  \"X_BANMA_MASTER_APP_SCOPES\":\""+this.getX_BANMA_MASTER_APP_STATUS()+"\"\n" +
                "}";
    }
}
