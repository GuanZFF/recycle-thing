package pers.zhenfeng.api.fallback;

import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.RecycleUserBO;
import pers.zhenfeng.api.service.RecycleUserService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

/**
 * @author Grow-Worm
 * @date 2019/01/16
 */
@Component
public class RecycleUserServiceFallback implements RecycleUserService {

    @Override
    public BaseResult<RecycleUserBO> getUserByNo(String userNo) {
        return BaseResultUtil.fallback();
    }

    @Override
    public BaseResult<RecycleUserBO> insert(RecycleUserBO recycleUserBO) {
        return BaseResultUtil.fallback();
    }

}
