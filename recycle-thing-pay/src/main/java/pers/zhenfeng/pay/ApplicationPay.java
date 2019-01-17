package pers.zhenfeng.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Grow-Worm
 * @date 2019/01/17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationPay {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPay.class, args);
    }
}
