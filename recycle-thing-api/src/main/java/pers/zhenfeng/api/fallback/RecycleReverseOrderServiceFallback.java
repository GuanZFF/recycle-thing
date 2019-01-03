package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryReverseOrderParam;
import pers.zhenfeng.api.bo.RecycleReverseOrderBO;
import pers.zhenfeng.api.service.RecycleReverseOrderService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2019/01/02
 */
@Component
public class RecycleReverseOrderServiceFallback implements RecycleReverseOrderService {

    @Override
    public BaseResult<Integer> insert(RecycleReverseOrderBO recycleReverseOrderBO) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<List<RecycleReverseOrderBO>> getReverseOrders(String uid) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<BasePage<RecycleReverseOrderBO>> getReverseOrderPage(QueryReverseOrderParam param) {
        return BaseResultUtil.fallback(BasePageUtil.<RecycleReverseOrderBO>emptyPage());
    }
}
