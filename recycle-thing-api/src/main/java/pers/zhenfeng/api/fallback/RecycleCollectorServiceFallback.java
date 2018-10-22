package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@Component
public class RecycleCollectorServiceFallback implements RecycleCollectorService {

    @Override
    public BaseResult<RecycleCollectorBO> getRecycleCollector(String id) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<RecycleCollectorBO> getRecycleCollectorByNo(String collectorNo) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<List<RecycleCollectorBO>> getAllRecycleCollector() {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> insertRecycleCollector(RecycleCollectorBO recycleCollectorBO) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<Integer> deleteRecycleCollector(String collectorNo) {
        return BaseResultUtil.fallback();
    }
}
