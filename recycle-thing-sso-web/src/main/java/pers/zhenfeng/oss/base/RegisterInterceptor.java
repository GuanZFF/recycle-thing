package pers.zhenfeng.oss.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Grow-Worm
 * @date 2018/10/12
 */
@Configuration
public class RegisterInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OSSInterceptor());
        super.addInterceptors(registry);
    }

}