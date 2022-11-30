package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TokenResponseDTO {
    @JsonProperty(value = "AppID")
    String appID;
    @JsonProperty(value = "AccessToken")
    String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "AccessTokenExpiryTime")
    DateTime accessTokenExpiryTime;
    @JsonProperty(value = "RefreshToken")
    String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "RefreshTokenExpiryTime")
    DateTime refreshTokenExpiryTime;
    @JsonProperty(value = "Scopes")
    String[] scopes;
}