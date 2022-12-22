package com.hrghs.xycb.services;

import com.hrghs.xycb.aops.RestTemplateInterceptor;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_APPINFO;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_PREFIX;
import static jodd.util.StringPool.COLON;
import static jodd.util.StringPool.DASH;

public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    @Lazy
    private BanmaerpPropertiesService banmaerpPropertiesService;
    @Autowired
    private ApplicationContext context;
    @Autowired
    @Lazy
    private ReactiveRedisOperations<String, BanmaerpProperties> bmerp_redisOps;
    @EventListener(ApplicationReadyEvent.class)
    public void ready(){
        logger.info("Banmaerp module integrating successfully!");
        BanmaerpProperties banmaerpProperties = banmaerpPropertiesService.getPlatformProperties();
        banmaerpPropertiesService.saveBanmaerpProperties(banmaerpProperties);
        banmaerpPropertiesService.findAll().parallelStream().forEach(bmerp_pros ->
                bmerp_redisOps.opsForValue().set(BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_APPINFO)
                        .concat(COLON).concat(bmerp_pros.getX_BANMA_MASTER_APP_ACCOUNT()).concat(DASH)
                        .concat(bmerp_pros.getX_BANMA_MASTER_APP_ID())
                        ,bmerp_pros)
                        .subscribe()
        );
    }
}
