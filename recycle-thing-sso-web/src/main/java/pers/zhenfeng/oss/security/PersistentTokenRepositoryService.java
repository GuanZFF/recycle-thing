package pers.zhenfeng.oss.security;

import com.alibaba.fastjson.JSON;
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

    private RecycleTokenService recycleTokenService;

    public PersistentTokenRepositoryService(RecycleTokenService recycleTokenService) {
        this.recycleTokenService = recycleTokenService;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        RedisUtil.setValue(token.getSeries(), JSON.toJSONString(token));

        SsoTokenBO ssoTokenBO = new SsoTokenBO();
        ssoTokenBO.setSeries(token.getSeries());
        ssoTokenBO.setDate(token.getDate());
        ssoTokenBO.setTokenValue(token.getTokenValue());
        ssoTokenBO.setUsername(token.getUsername());

        recycleTokenService.insertToken(ssoTokenBO);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String token = RedisUtil.getValueByKey(series);
        if (StringUtils.isEmpty(token)) {
            return;
        }
        PersistentRememberMeToken oldToken = JSON.parseObject(token, PersistentRememberMeToken.class);

        PersistentRememberMeToken newToken = new PersistentRememberMeToken(oldToken.getUsername(), series, tokenValue, lastUsed);

        RedisUtil.setValue(newToken.getSeries(), JSON.toJSONString(token));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String token = RedisUtil.getValueByKey(seriesId);
        if (!StringUtils.isEmpty(token)) {
            return JSON.parseObject(token, PersistentRememberMeToken.class);
        }

        BaseResult<SsoTokenBO> tokenResult = recycleTokenService.getTokenBySeries(seriesId);
        if (BaseResultUtil.isFail(tokenResult)) {
            return null;
        }
        SsoTokenBO tokenBO = tokenResult.getData();
        return new PersistentRememberMeToken(tokenBO.getUsername(), tokenBO.getSeries(), tokenBO.getTokenValue(), tokenBO.getDate());
    }

    @Override
    public void removeUserTokens(String username) {
        RedisUtil.remove(username);
    }
}
