package com.hrghs.xycb.domains;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GetTokenResponse extends Object {
    String AppID;
    String AccessToken;
    DateTime AccessTokenExpiryTime;
    String RefreshToken;
    DateTime RefreshTokenExpiryTime;
    String Scopes;
}
