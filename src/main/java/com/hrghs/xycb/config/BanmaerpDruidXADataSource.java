package com.hrghs.xycb.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.banmaerp.druid")
public class BanmaerpDruidXADataSource extends DruidXADataSource {
}
