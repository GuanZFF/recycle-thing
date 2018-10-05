package pers.zhenfeng.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "pers.zhenfeng.api.service")
@ComponentScan(basePackages = {"pers.zhenfeng.api", "pers.zhenfeng.oss"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
