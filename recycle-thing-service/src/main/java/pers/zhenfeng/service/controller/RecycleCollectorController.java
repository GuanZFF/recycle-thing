package pers.zhenfeng.service.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.mapper.RecycleCollectorMapper;
import pers.zhenfeng.service.po.RecycleCollectorPO;

import javax.annotation.Resource;

@RestController
@RequestMapping("collector")
public class RecycleCollectorController {

    @Resource
    private RecycleCollectorMapper recycleCollectorMapper;

    @RequestMapping("getRecycleCollector")
    public BaseResult<RecycleCollectorBO> getRecycleCollector(@RequestParam("id") Integer id) {
        RecycleCollectorPO recycleCommodityPO = recycleCollectorMapper.getRecycleCollector(id);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.success();
        }

        RecycleCollectorBO recycleCollectorBO = new RecycleCollectorBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCollectorBO);

        return BaseResultUtil.success(recycleCollectorBO);
    }

}
