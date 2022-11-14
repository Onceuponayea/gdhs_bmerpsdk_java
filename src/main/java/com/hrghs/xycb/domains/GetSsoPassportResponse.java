package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GetSsoPassportResponse {
    @JsonProperty(value = "UserID")
    private String userId;
    @JsonProperty(value = "AppID")
    private String appId;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("ClientIP")
    private String clientIp;
    @JsonProperty(value = "SsoToken")
    private String ssoToken;
    @JsonProperty("SsoUrl")
    private String ssoUrl;
}
