package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.CommodityStatus;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.service.constant.NumberManage;
import pers.zhenfeng.service.mapper.NumberManageMapper;
import pers.zhenfeng.service.mapper.RecycleCommodityMapper;
import pers.zhenfeng.service.po.NumberManagePO;
import pers.zhenfeng.service.po.RecycleCommodityPO;
import pers.zhenfeng.service.service.CommonService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/commodity")
public class RecycleCommodityController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RecycleCommodityController.class);

    @Resource
    private RecycleCommodityMapper recycleCommodityMapper;

    @Resource
    private CommonService commonService;

    @RequestMapping("getRecycleCommodity/{id}")
    public BaseResult<RecycleCommodityBO> getRecycleCommodityById(@PathVariable("id") Integer id) {
        RecycleCommodityPO recycleCommodityPO = recycleCommodityMapper.getRecycleCommodity(id);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.success();
        }

        RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCommodityBO);

        return BaseResultUtil.success(recycleCommodityBO);
    }

    @RequestMapping("getRecycleCommodity")
    public BaseResult<RecycleCommodityBO> getRecycleCommodity(@RequestParam("commodityNo") String commodityNo) {
        LOGGER.error("getRecycleCommodity");
        RecycleCommodityPO recycleCommodityPO = recycleCommodityMapper.getRecycleCommodityByNo(commodityNo);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.success();
        }

        RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCommodityBO);

        return BaseResultUtil.success(recycleCommodityBO);
    }

    @RequestMapping(value = "getRecycleCommodityPage", method = RequestMethod.POST)
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestBody QueryCommodityParam param) {
        Integer index = (param.getPageNum() - 1) * param.getPageSize();

        Integer count = recycleCommodityMapper.getRecycleCommodityPageCount(param);
        if (count <= 0) {
            return BaseResultUtil.success(BasePageUtil.emptyPage());
        }

        List<RecycleCommodityPO> list = recycleCommodityMapper.getRecycleCommodityPage(index, param.getPageSize(), param);

        List<RecycleCommodityBO> recycleCommodityBOS = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return BaseResultUtil.success(BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleCommodityBOS));
        }

        BeanUtils.copyProperties(list, recycleCommodityBOS);
        list.forEach(item -> {
            RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();
            BeanUtils.copyProperties(item, recycleCommodityBO);
            recycleCommodityBOS.add(recycleCommodityBO);
        });

        BasePage<RecycleCommodityBO> basePage = BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleCommodityBOS);

        return BaseResultUtil.success(basePage);
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public BaseResult<Integer> insertRecycleCommodity(@RequestBody RecycleCommodityBO recycleCommodityBO) {
        RecycleCommodityPO recycleCommodityPO = new RecycleCommodityPO();
        BeanUtils.copyProperties(recycleCommodityBO, recycleCommodityPO);

        // 生成商品编号
        String commodityNo = NumberUtil.generateCommodityNo(commonService.getNumber(NumberManage.COMMODITY.getKey()));

        recycleCommodityPO.setCommodityNo(commodityNo);
        recycleCommodityPO.setCommodityStatus(CommodityStatus.INIT.getCode());

        Integer id = recycleCommodityMapper.insert(recycleCommodityPO);

        return BaseResultUtil.success(id);
    }

    @RequestMapping("updateCommodityToStart")
    public BaseResult<Integer> updateCommodityToStart(@RequestParam("commodityNo") String commodityNo) {
        Integer updateCol = recycleCommodityMapper.updateCommodityToStart(commodityNo);
        if (updateCol > 0) {
            return BaseResultUtil.success(updateCol);
        }
        return BaseResultUtil.fail("更新失败");
    }

    @RequestMapping("updateCommodityToStop")
    public BaseResult<Integer> updateCommodityToStop(@RequestParam("commodityNo") String commodityNo) {
        Integer updateCol = recycleCommodityMapper.updateCommodityToStop(commodityNo);
        if (updateCol > 0) {
            return BaseResultUtil.success(updateCol);
        }
        return BaseResultUtil.fail("更新失败");
    }

}
