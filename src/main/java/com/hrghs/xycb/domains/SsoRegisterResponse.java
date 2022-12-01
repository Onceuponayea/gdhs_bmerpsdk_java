package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.AppsInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SsoRegisterResponse {

    @JsonProperty("Account")
    private AccountDTO account;
    @JsonProperty("App")
    private AppsInfoDTO app;
    @JsonProperty("Auth")
    private TokenResponseDTO auth;

    public BanmaerpProperties toBanmaerpProperties(){
        BanmaerpProperties banmaerpProperties = new BanmaerpProperties();
        banmaerpProperties.setX_BANMA_MASTER_APP_ID(app.getID());
        banmaerpProperties.setX_BANMA_APP_NAME(app.getName());
        banmaerpProperties.setX_BANMA_MASTER_APP_SECRET(app.getSecret());
        banmaerpProperties.setX_BANMA_MASTER_APP_ACCOUNT(account.getPhone());
        banmaerpProperties.setBanmaErpAccounts(Arrays.asList(account));
        return banmaerpProperties;
    }
}
