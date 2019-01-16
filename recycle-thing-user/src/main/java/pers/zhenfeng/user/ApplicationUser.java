package pers.zhenfeng.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Grow-Worm
 * @date 2019/01/16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationUser {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUser.class, args);
    }

}
