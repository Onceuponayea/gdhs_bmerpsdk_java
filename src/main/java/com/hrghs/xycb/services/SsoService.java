package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;

public interface SsoService {

    /**
     * 获取认证令牌
     * @param account 登录账号（手机号或者邮箱，必填）
     * @param clientIp 用户客户端IP（必填）
     * @param clientIp 用户ID
     * @param mode
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * 可用值：
     * 0=返回认证令牌(默认值)
     * 1=浏览器直接跳转
     * @return
     */

    GetSsoPassportResponse ssoPassport(
            String account,
            String clientIp,
            int userId,
            int mode,
            BanmaerpProperties banmaerpProperties
    );

}
