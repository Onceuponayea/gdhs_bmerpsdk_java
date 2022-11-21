package com.hrghs.xycb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * https://piotrminkowski.com/2020/08/04/guide-to-building-spring-boot-library/
 * https://medium.com/trendyol-tech/how-to-write-a-spring-boot-library-project-7064e831b63b
 *
 */

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
