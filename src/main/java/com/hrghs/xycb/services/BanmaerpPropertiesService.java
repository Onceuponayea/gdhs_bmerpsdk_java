package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;

import java.util.List;

public interface BanmaerpPropertiesService {

    public void initBmerpAppInfos();

    public BanmaerpProperties getByPhone(Long phone);

    public BanmaerpProperties getPlatformProperties();

    public BanmaerpProperties saveBanmaerpProperties(BanmaerpProperties banmaerpProperties);

    public BanmaerpProperties saveBanmaerpProperties(Long phone, String appId,String appSecret);

    public List<BanmaerpProperties> findAll();
}
