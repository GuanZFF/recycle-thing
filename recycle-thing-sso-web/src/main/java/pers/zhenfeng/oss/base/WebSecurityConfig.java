package pers.zhenfeng.oss.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.zhenfeng.core.util.EncodeUtil;

/**
 * @author Grow-Worm
 * @date 2018/11/08
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public PasswordEncoder myPasswordEncode() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                String encodedPassword = rawPassword.toString();

                try {
                    encodedPassword = EncodeUtil.md5(rawPassword.toString());
                } catch (Exception ex) {
                    LOGGER.error("加密出错", ex);
                }

                return encodedPassword;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(encode(rawPassword));
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserService()).passwordEncoder(myPasswordEncode());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/recycle/**").permitAll()
                .antMatchers("/**").hasAuthority("user")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/recycle/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
