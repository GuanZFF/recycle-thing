package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.api.service.RecycleOrderService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
@Component
public class RecycleOrderServiceFallback implements RecycleOrderService {

    @Override
    public BaseResult<Integer> insert(RecycleOrderBO recycleOrderBO) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<List<RecycleOrderBO>> getRecycleOrderList(String uid) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<BasePage<RecycleOrderBO>> getRecycleOrderPage(QueryOrderParam param) {
        return BaseResultUtil.fallback(BasePageUtil.<RecycleOrderBO>emptyPage());
    }

}
