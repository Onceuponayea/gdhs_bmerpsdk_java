package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import com.hrghs.xycb.services.BanmaerpPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import java.util.concurrent.TimeUnit;

public class BanmaerpPropertiesServiceImpl implements BanmaerpPropertiesService {
    @Autowired
    private BanmaerpPropertiesRepository repository;
    @Autowired
    private RedisOperations<Long,BanmaerpProperties> redisOperations;

    @Override
    public BanmaerpProperties getByPhone(Long phone) {
        BanmaerpProperties banmaerpProperties = redisOperations.opsForValue().get(phone);
        banmaerpProperties = banmaerpProperties==null?
                repository.findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNT(phone.toString()):
                banmaerpProperties;
        redisOperations.opsForValue().set(phone,banmaerpProperties,1, TimeUnit.DAYS);
        return banmaerpProperties;
    }
}
