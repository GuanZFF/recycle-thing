package pers.zhenfeng.web.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.CommonService;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.api.service.RecycleCommodityService;
import pers.zhenfeng.api.service.RecycleService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.CommodityStatus;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.web.util.BOVOUtil;
import pers.zhenfeng.web.vo.RecycleCommodityVO;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/09/18
 */
@RestController
@RequestMapping("/commodity")
public class RecycleCommodityController {

    @Resource
    private RecycleCommodityService recycleCommodityService;

    @Resource
    private RecycleCollectorService recycleCollectorService;

    @Resource
    private CommonService commonService;

    @RequestMapping("/getRecycleCommodity")
    public BaseResult<RecycleCommodityVO> getRecycleCommodity(String commodityNo) {
        if (StringUtils.isEmpty(commodityNo)) {
            return BaseResultUtil.failParam();
        }

        // 获取商品详情
        BaseResult<RecycleCommodityBO> result = recycleCommodityService.getRecycleCommodity(commodityNo);
        if (BaseResultUtil.isFail(result)) {
            return BaseResultUtil.fail(result.getMsg());
        }
        RecycleCommodityBO recycleCommodityBO = result.getData();
        RecycleCommodityVO recycleCommodityVO = BOVOUtil.buildRecycleCommodityVO(recycleCommodityBO);

        // 设置商品类型
        BaseResult<CommodityTypeBO> typeResult = commonService.getCommodityType(recycleCommodityBO.getCommodityType());
        if (BaseResultUtil.isSuccess(typeResult)) {
            recycleCommodityVO.setCommodityTypeDesc(typeResult.getData().getName());
        }

        // 设置收集人信息
        BaseResult<RecycleCollectorBO> collectorResult = recycleCollectorService.getRecycleCollectorByNo(recycleCommodityBO.getCollectorNo());
        if (BaseResultUtil.isSuccess(collectorResult)) {
            recycleCommodityVO.setRecycleCollectorBO(collectorResult.getData());
        }

        return BaseResultUtil.success(recycleCommodityVO);
    }

    @RequestMapping("getRecycleCommodityPage")
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        QueryCommodityParam queryCommodityParam = new QueryCommodityParam();
        queryCommodityParam.setPageNum(pageNum);
        queryCommodityParam.setPageSize(pageSize);
        queryCommodityParam.setCommodityStatus(CommodityStatus.START.getCode());
        return recycleCommodityService.getRecycleCommodityPage(queryCommodityParam);
    }
}
