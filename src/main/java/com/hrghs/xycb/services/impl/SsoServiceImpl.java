package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.SsoService;

public class SsoServiceImpl implements SsoService {

    /**
     * 获取认证令牌
     * @param account 登录账号（手机号或者邮箱，必填）
     * @param clientIp 用户客户端IP（必填）
     * @param mode
     * 可用值：
     * 0=返回认证令牌(默认值)
     * 1=浏览器直接跳转
     * @return
     */
    @Override
    public BanmaErpResponseDTO ssoPassport(String account, String clientIp, int mode,
                                           BanmaerpProperties banmaerpProperties) {
        return null;
    }
}
