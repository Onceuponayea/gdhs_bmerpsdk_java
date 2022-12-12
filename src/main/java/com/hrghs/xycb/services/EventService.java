package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;

public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    @Lazy
    private BanmaerpPropertiesService banmaerpPropertiesService;
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        logger.info("Banmaerp module integrating successfully!");
        BanmaerpProperties banmaerpProperties = banmaerpPropertiesService.getPlatformProperties();
        banmaerpProperties = banmaerpPropertiesService.saveBanmaerpProperties(banmaerpProperties);


    }
}
