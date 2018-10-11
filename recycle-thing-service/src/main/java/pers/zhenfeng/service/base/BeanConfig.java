package pers.zhenfeng.service.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Grow-Worm
 * @date 2018/10/10
 */
@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService getExecutorService() {
        return Executors.newCachedThreadPool();
    }

}
