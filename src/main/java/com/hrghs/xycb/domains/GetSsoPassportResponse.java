package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
    private String userID;
    @JsonProperty(value = "AppID")
    private String appID;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("ClientIP")
    private String clientIP;
    @JsonProperty(value = "SsoToken")
    private String ssoToken;
    @JsonProperty("SsoUrl")
    private String ssoUrl;
}
