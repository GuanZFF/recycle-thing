package pers.zhenfeng.oss.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/11/08
 */
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String RECYCLE_LOGIN = "/recycle/login";

    private static final String RECYCLE_LOGIN_SUCCESS = "/recycle/loginSuccess";

    private static final String RECYCLE_LOGIN_FAIL = "/recycle/loginFail";

    private static final String COOKIE_NAME = "token";

    @Resource
    private UserService userService;

    @Resource
    private PersistentTokenRepositoryService tokenRepositoryService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new MyPasswordEncode());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/recycle/**").permitAll()
                .antMatchers("/**").hasAnyRole("user")
                .anyRequest().authenticated()
                .and()
                .rememberMe().tokenRepository(tokenRepositoryService).rememberMeCookieName(COOKIE_NAME).alwaysRemember(true)
                .and()
                .formLogin().loginPage(RECYCLE_LOGIN).failureForwardUrl(RECYCLE_LOGIN_FAIL).successForwardUrl(RECYCLE_LOGIN_SUCCESS).permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}
