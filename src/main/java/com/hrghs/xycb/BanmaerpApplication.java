package com.hrghs.xycb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * just for verify the application configurations
 * todo 1、saveOrUpdate duplicate key
 * 2、register重复的话就推送到配置好的企业微信群和钉钉
 * 3、物流接口
 * 4、店铺权限是空的
 */
@SpringBootApplication
public class BanmaerpApplication {
    public static void main( String[] args ) {
        SpringApplication springApplication = new SpringApplication(BanmaerpApplication.class);
        springApplication.setLazyInitialization(true);
        springApplication.run(args);
    }
}
