package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.SsoTokenBO;
import pers.zhenfeng.api.service.RecycleTokenService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@Component
public class RecycleTokenServiceFallback implements RecycleTokenService {


    @Override
    public BaseResult<SsoTokenBO> getTokenBySeries(String series) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> insertToken(SsoTokenBO tokenBO) {
        return BaseResultUtil.fallback();
    }
}
