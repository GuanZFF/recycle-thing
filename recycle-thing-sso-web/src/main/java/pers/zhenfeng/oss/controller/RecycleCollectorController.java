package pers.zhenfeng.oss.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.CollectorStatus;
import pers.zhenfeng.core.constant.ResultMsg;
import pers.zhenfeng.core.constant.SexStatus;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.oss.vo.RecycleCollectorVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
@RestController
@RequestMapping("/collector")
public class RecycleCollectorController {

    @Resource
    private RecycleCollectorService recycleCollectorService;


    @RequestMapping("/getAllRecycleCollector")
    public BaseResult<List<RecycleCollectorVO>> getAllRecycleCollector() {
        BaseResult<List<RecycleCollectorBO>> baseResult = recycleCollectorService.getAllRecycleCollector();

        if (BaseResultUtil.isSuccess(baseResult)) {
            List<RecycleCollectorVO> recycleCollectorBOS = baseResult.getData().stream().map(RecycleCollectorController::buildRecycleCollectorVO).collect(Collectors.toList());

            return BaseResultUtil.success(recycleCollectorBOS);
        }

        return BaseResultUtil.fail(baseResult.getMsg());
    }

    @RequestMapping("/insertRecycleCollector")
    public BaseResult<Integer> insertRecycleCollector(@RequestBody RecycleCollectorVO recycleCollectorVO) {
        if (ObjectUtils.isEmpty(recycleCollectorVO)) {
            return BaseResultUtil.fail("参数不存在");
        }

        RecycleCollectorBO recycleCollectorBO = new RecycleCollectorBO();
        BeanUtils.copyProperties(recycleCollectorVO, recycleCollectorBO);
        recycleCollectorService.insertRecycleCollector(recycleCollectorBO);

        return BaseResultUtil.success();
    }

    @RequestMapping("deleteRecycleCollector")
    public BaseResult<Integer> deleteRecycleCollector(@RequestParam("collectorNo") String collectorNo) {
        if (StringUtils.isEmpty(collectorNo)) {
            return BaseResultUtil.fail(ResultMsg.PARAM_ERROR.getMsg());
        }

        return recycleCollectorService.deleteRecycleCollector(collectorNo);
    }

    private static RecycleCollectorVO buildRecycleCollectorVO(RecycleCollectorBO recycleCollectorBO) {
        RecycleCollectorVO recycleCollectorVO = new RecycleCollectorVO();

        recycleCollectorVO.setSexDesc(SexStatus.getSexStatusDesc(recycleCollectorBO.getSex()));
        recycleCollectorVO.setStatusDesc(CollectorStatus.getCollectorStatusDesc(recycleCollectorBO.getStatus()));

        BeanUtils.copyProperties(recycleCollectorBO, recycleCollectorVO);

        return recycleCollectorVO;
    }

}
