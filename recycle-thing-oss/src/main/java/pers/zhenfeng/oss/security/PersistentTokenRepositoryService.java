package pers.zhenfeng.oss.security;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pers.zhenfeng.api.bo.SsoTokenBO;
import pers.zhenfeng.api.service.RecycleTokenService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.RedisUtil;

import java.util.Date;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@Component
public class PersistentTokenRepositoryService implements PersistentTokenRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersistentTokenRepositoryService.class);

    private RecycleTokenService recycleTokenService;

    public PersistentTokenRepositoryService(RecycleTokenService recycleTokenService) {
        this.recycleTokenService = recycleTokenService;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        try {
            RedisUtil.setValue(token.getSeries(), JSON.toJSONString(token));
        } catch (Exception e) {
            LOGGER.error("redis存储token失败", e);
        }

        try {
            SsoTokenBO ssoTokenBO = new SsoTokenBO();
            ssoTokenBO.setSeries(token.getSeries());
            ssoTokenBO.setDate(token.getDate());
            ssoTokenBO.setTokenValue(token.getTokenValue());
            ssoTokenBO.setUsername(token.getUsername());

            recycleTokenService.insertToken(ssoTokenBO);
        } catch (Exception e) {
            LOGGER.error("database存储token失败", e);
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        try {
            String token = RedisUtil.getValueByKey(series);
            if (StringUtils.isEmpty(token)) {
                return;
            }
            PersistentRememberMeToken oldToken = JSON.parseObject(token, PersistentRememberMeToken.class);

            PersistentRememberMeToken newToken = new PersistentRememberMeToken(oldToken.getUsername(), series, tokenValue, lastUsed);

            RedisUtil.setValue(newToken.getSeries(), JSON.toJSONString(token));
        } catch (Exception e) {
            LOGGER.error("redis更新token失败", e);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            String token = RedisUtil.getValueByKey(seriesId);
            if (!StringUtils.isEmpty(token)) {
                return JSON.parseObject(token, PersistentRememberMeToken.class);
            }
        } catch (Exception e) {
            LOGGER.error("redis获取token失败", e);
        }

        try {
            BaseResult<SsoTokenBO> tokenResult = recycleTokenService.getTokenBySeries(seriesId);
            if (BaseResultUtil.isFail(tokenResult)) {
                return null;
            }
            SsoTokenBO tokenBO = tokenResult.getData();
            return new PersistentRememberMeToken(tokenBO.getUsername(), tokenBO.getSeries(), tokenBO.getTokenValue(), tokenBO.getDate());
        } catch (Exception e) {
            LOGGER.error("redis获取token失败", e);
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        try {
            RedisUtil.remove(username);
        } catch (Exception e) {
            LOGGER.error("redis删除token失败", e);
        }
    }
}
