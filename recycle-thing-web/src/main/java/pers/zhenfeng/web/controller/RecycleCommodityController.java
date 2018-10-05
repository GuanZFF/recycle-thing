package pers.zhenfeng.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.RecycleCommodityService;
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

    @RequestMapping("/getRecycleCommodity")
    public BaseResult<RecycleCommodityVO> getRecycleCommodity(String commodityNo) {
        BaseResult<RecycleCommodityBO> result = recycleCommodityService.getRecycleCommodity(commodityNo);
        if (BaseResultUtil.isSuccess(result)) {
            RecycleCommodityVO recycleCommodityVO = BOVOUtil.buildRecycleCommodityVO(result.getData());
            return BaseResultUtil.success(recycleCommodityVO);
        }
        return BaseResultUtil.fail(result.getMsg());
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
