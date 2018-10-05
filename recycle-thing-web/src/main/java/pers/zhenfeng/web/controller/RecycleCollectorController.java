package pers.zhenfeng.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.web.vo.RecycleCollectorVO;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@RestController
@RequestMapping("/collector")
public class RecycleCollectorController {

    @Resource
    private RecycleCollectorService recycleCollectorService;

    @RequestMapping("getRecycleCollector")
    public BaseResult<RecycleCollectorVO> getRecycleCollector(@RequestParam("collectorNo") String collectorNo) {
        BaseResult<RecycleCollectorBO> recycleCollectorByNo = recycleCollectorService.getRecycleCollectorByNo(collectorNo);

        if (BaseResultUtil.isSuccess(recycleCollectorByNo)) {
            RecycleCollectorVO recycleCollectorVO = new RecycleCollectorVO();
            BeanUtils.copyProperties(recycleCollectorByNo.getData(), recycleCollectorVO);
            return BaseResultUtil.success(recycleCollectorVO);
        }

        return BaseResultUtil.fail(recycleCollectorByNo.getMsg());
    }

}
