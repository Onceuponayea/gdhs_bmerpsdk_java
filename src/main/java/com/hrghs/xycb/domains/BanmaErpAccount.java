package com.hrghs.xycb.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanmaErpAccount {
    private  String x_banma_master_app_id;
    private  String x_banma_app_id;
    private  String x_banma_app_secret;
    private  String x_banma_app_account;
    private  String x_banma_account_realName;
    private  String x_banma_account_status;
    private  String x_banma_account_department;
    private List<String> permissionList;
    private List<String> roleList;
}
