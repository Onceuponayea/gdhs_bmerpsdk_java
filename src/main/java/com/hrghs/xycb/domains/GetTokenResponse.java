package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GetTokenResponse {
    @JsonProperty(value = "AppID")
    String appID;
    @JsonProperty(value = "AccessToken")
    String accessToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "AccessTokenExpiryTime")
    DateTime accessTokenExpiryTime;
    @JsonProperty(value = "RefreshToken")
    String refreshToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "RefreshTokenExpiryTime")
    DateTime refreshTokenExpiryTime;
    @JsonProperty(value = "Scopes")
    String[] scopes;
}
