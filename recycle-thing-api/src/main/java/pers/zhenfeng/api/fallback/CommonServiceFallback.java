package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.bo.RecycleLogBO;
import pers.zhenfeng.api.service.CommonService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/05
 */
@Component
public class CommonServiceFallback implements CommonService {

    @Override
    public BaseResult<List<CommodityTypeBO>> getAllCommodityType() {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> insertCommodityType(CommodityTypeBO commodityTypeBO) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Void> insertLog(RecycleLogBO recycleLogBO) {
        return BaseResultUtil.fallback();
    }

}
