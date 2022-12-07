package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.AppsInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SsoRegisterRequest {

    private AccountDTO account;

    private AppsInfoDTO app;
}
