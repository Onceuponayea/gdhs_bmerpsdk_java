package com.hrghs.xycb.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * banma erp account (master account, sub-account and service provider account - 选品中心)
 */
public class BanmaErpAccount {
    private  String x_banma_master_app_id;
    private  String x_banma_app_id;
    private  String x_banma_app_secret;
    private  String x_banma_app_account;
    @JsonProperty("ID")
    private Long x_banma_accountID;
    @JsonProperty("Phone")
    private  String x_banma_app_phone;
    @JsonProperty("Email")
    private  String x_banma_app_email;
    @JsonProperty("RealName")
    private  String x_banma_account_realName;
    private  String x_banma_account_status;
    @JsonProperty("Department")
    private  String x_banma_account_department;
    private List<String> permissionList;
    private List<String> roleList;
}
