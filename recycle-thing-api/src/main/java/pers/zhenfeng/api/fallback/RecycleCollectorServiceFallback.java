package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@Component
public class RecycleCollectorServiceFallback implements RecycleCollectorService {

    @Override
    public BaseResult<RecycleCollectorBO> getRecycleCollector(Integer id) {
        return BaseResultUtil.fallback();
    }

}
