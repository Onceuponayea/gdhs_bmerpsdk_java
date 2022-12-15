package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import com.hrghs.xycb.services.BanmaerpPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import java.time.Duration;

import static com.hrghs.xycb.domains.Constants.BANMAERP_MESSAGE_CONFIGURATION_ERROR;

public class BanmaerpPropertiesServiceImpl implements BanmaerpPropertiesService {
    private static final Logger logger = LoggerFactory.getLogger(BanmaerpPropertiesServiceImpl.class);
    @Autowired
    @Lazy
    private BanmaerpPropertiesRepository repository;
    @Autowired
    @Lazy
    private ReactiveRedisOperations<String,BanmaerpProperties> redisOperations;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public BanmaerpProperties getByPhone(Long phone) {
        return
        redisOperations.opsForValue().get(phone.toString()).defaultIfEmpty(repository.findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNT(phone.toString()))
        .map(banmaerpProperties -> {
            redisOperations.opsForValue().set(phone.toString(),banmaerpProperties, Duration.ofDays(1));
            return banmaerpProperties;
        }).block();
    }

    @Override
    public BanmaerpProperties getPlatformProperties() {
        BanmaerpProperties  banmaerpProperties = applicationContext.getBean("erp.banmaerp-com.hrghs.xycb.domains.BanmaerpProperties",BanmaerpProperties.class);
        if (banmaerpProperties==null){
            throw new IllegalArgumentException(BANMAERP_MESSAGE_CONFIGURATION_ERROR);
        }
        logger.info("获取平台默认的banmaerpProperties\t{}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT());
        return banmaerpProperties;
    }

    @Override
    public BanmaerpProperties saveBanmaerpProperties(BanmaerpProperties banmaerpProperties) {
        return repository.saveAndFlush(banmaerpProperties);
    }
}
