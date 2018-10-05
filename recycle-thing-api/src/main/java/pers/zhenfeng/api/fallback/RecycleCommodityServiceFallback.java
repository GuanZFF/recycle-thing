package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.RecycleCommodityService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;

@Component
public class RecycleCommodityServiceFallback implements RecycleCommodityService {

    @Override
    public BaseResult<RecycleCommodityBO> getRecycleCommodity(String commodityNo) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(QueryCommodityParam param) {
        return BaseResultUtil.success(BasePageUtil.<RecycleCommodityBO>emptyPage());
    }


    @Override
    public BaseResult<Integer> insertRecycleCommodity(RecycleCommodityBO recycleCommodityBO) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> updateCommodityToStop(String commodityNo) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> updateCommodityToStart(String commodityNo) {
        return BaseResultUtil.fallback();
    }

}
