package pers.zhenfeng.web.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.service.CommonService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.web.util.BOVOUtil;
import pers.zhenfeng.web.vo.CommodityTypeVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grow-Worm
 * @date 2018/10/05
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;

    @RequestMapping("/getAllCommodityType")
    public BaseResult<List<CommodityTypeVO>> getAllCommodityTypeVO() {
        BaseResult<List<CommodityTypeBO>> baseResult = commonService.getAllCommodityType();
        if (BaseResultUtil.isFail(baseResult)) {
            return BaseResultUtil.fail(baseResult.getMsg());
        }

        List<CommodityTypeBO> commodityTypeBOS = baseResult.getData();
        if (CollectionUtils.isEmpty(commodityTypeBOS)) {
            return BaseResultUtil.success();
        }

        List<CommodityTypeVO> commodityTypeVOS = commodityTypeBOS.stream().map(BOVOUtil::buildCommodityTypeVO).collect(Collectors.toList());

        return BaseResultUtil.success(commodityTypeVOS);
    }

}
