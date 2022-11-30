package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RegisterResponseDTO {
    @JsonProperty("Account")
    private AccountDTO banmaErpAccount;
    @JsonProperty("App")
    private AppsInfoDTO appsInfoDTO;
    @JsonProperty("Auth")
    private TokenResponseDTO tokenResponseDTO;
}
