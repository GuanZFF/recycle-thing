package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.bo.RecycleLogBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.base.LogUtil;
import pers.zhenfeng.service.mapper.CommodityTypeMapper;
import pers.zhenfeng.service.mapper.NumberManageMapper;
import pers.zhenfeng.service.po.CommodityTypePO;
import pers.zhenfeng.service.po.NumberManagePO;
import pers.zhenfeng.service.po.RecycleLogPO;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @Resource
    private CommodityTypeMapper commodityTypeMapper;

    @RequestMapping("getAllCommodityType")
    public BaseResult<List<CommodityTypeBO>> getAllCommodityType() {
        List<CommodityTypePO> commodityTypePOS = commodityTypeMapper.getAllCommodityType();
        if (CollectionUtils.isEmpty(commodityTypePOS)) {
            return BaseResultUtil.success();
        }

        List<CommodityTypeBO> commodityTypeBOS = Lists.newArrayList();
        commodityTypePOS.forEach(commodityTypePO -> {
            CommodityTypeBO commodityTypeBO = new CommodityTypeBO();
            BeanUtils.copyProperties(commodityTypePO, commodityTypeBO);
            commodityTypeBOS.add(commodityTypeBO);
        });

        return BaseResultUtil.success(commodityTypeBOS);
    }

    @RequestMapping("insertCommodityType")
    public BaseResult<Integer> insertCommodityType(@RequestBody CommodityTypeBO commodityTypeBO) {
        CommodityTypePO commodityTypePO = new CommodityTypePO();
        BeanUtils.copyProperties(commodityTypeBO, commodityTypePO);
        Integer insertCommodityType = commodityTypeMapper.insertCommodityType(commodityTypePO);

        return BaseResultUtil.success(insertCommodityType);
    }

    @RequestMapping("deleteCommodityType")
    public BaseResult<Integer> deleteCommodityType(@RequestParam("id") Integer id) {
        commodityTypeMapper.deleteCommodityType(id);
        return BaseResultUtil.success();
    }

    @RequestMapping("insertLog")
    public BaseResult<Void> insertLog(@RequestBody RecycleLogBO recycleLogBO) {
        if (ObjectUtils.isEmpty(recycleLogBO)) {
            return BaseResultUtil.success();
        }

        RecycleLogPO recycleLogPO = new RecycleLogPO();
        BeanUtils.copyProperties(recycleLogBO, recycleLogPO);
        LogUtil.insertLog(recycleLogPO);

        return BaseResultUtil.success();
    }

}
