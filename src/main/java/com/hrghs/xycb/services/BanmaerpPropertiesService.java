package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;

public interface BanmaerpPropertiesService {

    public BanmaerpProperties getByPhone(Long phone);

    public BanmaerpProperties getPlatformProperties();

    public BanmaerpProperties saveBanmaerpProperties(BanmaerpProperties banmaerpProperties);
}
