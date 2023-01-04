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
import org.springframework.data.redis.core.ScanOptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.*;

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
        String redisKeyPrefix = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_APPINFO).concat(COLON)
                .concat(phone.toString());
        String redisKeysPattern =redisKeyPrefix.concat(DASH).concat(STAR);
        /** todo 如果用户量超过10w级的话可能需要把keys替换成游标的方式来分步进行过滤，否则会造成性能影响 **/
        String bmerp_pros_redisKey =
        redisOperations.keys(redisKeysPattern)
                .switchIfEmpty(Mono.defer(()->{
                    BanmaerpProperties bmerp_pros = repository.findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNT(phone.toString());
                    if (bmerp_pros!=null){
                        String redisKey= redisKeyPrefix.concat(DASH).concat(bmerp_pros.getX_BANMA_MASTER_APP_ID());
                        redisOperations.opsForValue().set(redisKeyPrefix.concat(DASH).concat(bmerp_pros.getX_BANMA_MASTER_APP_ID()),bmerp_pros)
                                .subscribe();
                        return Mono.just(redisKey);
                    }else{
                        return Mono.error(new RuntimeException(BANMAERP_MESSAGE_ILLEGAL_ARGS_BanmaerpProperties));
                    }
                }))
                .blockFirst();
         return redisOperations.opsForValue().get(bmerp_pros_redisKey).block();
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

    @Override
    public BanmaerpProperties saveBanmaerpProperties(Long phone, String appId, String appSecret) {
        BanmaerpProperties banmaerpProperties = new BanmaerpProperties();
        banmaerpProperties.setX_BANMA_MASTER_APP_ACCOUNT(phone.toString());
        banmaerpProperties.setX_BANMA_MASTER_APP_ID(appId);
        banmaerpProperties.setX_BANMA_MASTER_APP_SECRET(appSecret);
        return saveBanmaerpProperties(banmaerpProperties);
    }

    @Override
    public List<BanmaerpProperties> findAll() {
        return repository.findAll();
    }
}
