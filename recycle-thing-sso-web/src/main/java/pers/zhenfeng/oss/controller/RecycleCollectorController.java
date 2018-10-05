package pers.zhenfeng.oss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;
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
            List<RecycleCollectorVO> recycleCollectorBOS = baseResult.getData().stream().map(item -> {
                RecycleCollectorVO recycleCollectorVO = new RecycleCollectorVO();
                recycleCollectorVO.setCollectorNo(item.getCollectorNo());
                recycleCollectorVO.setUsername(item.getUsername());
                return recycleCollectorVO;
            }).collect(Collectors.toList());

            return BaseResultUtil.success(recycleCollectorBOS);
        }

        return BaseResultUtil.fail(baseResult.getMsg());
    }

}
