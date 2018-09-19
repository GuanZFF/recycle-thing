package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.RecycleCommodityService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;

@Component
public class RecycleCommodityServiceFallback implements RecycleCommodityService {

    @Override
    public BaseResult<RecycleCommodityBO> getRecycleCommodity(Integer id) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(Integer pageNum, Integer pageSize) {
        return BaseResultUtil.success(BasePageUtil.<RecycleCommodityBO>emptyPage());
    }

}
