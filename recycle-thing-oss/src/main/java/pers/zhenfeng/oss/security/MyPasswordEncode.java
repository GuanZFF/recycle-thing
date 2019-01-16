package pers.zhenfeng.oss.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.zhenfeng.core.util.EncodeUtil;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@Component
public class MyPasswordEncode implements PasswordEncoder {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyPasswordEncode.class);

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

}
