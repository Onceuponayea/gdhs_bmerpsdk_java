package com.hrghs.xycb.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@ConditionalOnProperty(value = "enabled",prefix = "com.hrghs.xycb.banmaerp", havingValue = "true", matchIfMissing = false)
public class BanmaerpStarter {

}
