package com.hrghs.xycb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * just for verify the application configurations
 */
@SpringBootApplication
public class BanmaerpApplication {
    public static void main( String[] args ) {
        SpringApplication springApplication = new SpringApplication(BanmaerpApplication.class);
        springApplication.setLazyInitialization(true);
        springApplication.run(args);
    }
}
