package pers.zhenfeng.oss.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.api.service.RecycleCommodityService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.CommodityStatus;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.DateUtil;
import pers.zhenfeng.oss.vo.CommodityParam;
import pers.zhenfeng.oss.vo.RecycleCommodityVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Grow-Worm
 * @date 2018/09/29
 */
@RestController
@RequestMapping("/commodity")
public class RecycleCommodityController {

    @Resource
    private RecycleCommodityService recycleCommodityService;

    @Resource
    private RecycleCollectorService recycleCollectorService;

    @RequestMapping("getRecycleCommodityPage")
    public BaseResult<BasePage<RecycleCommodityVO>> getRecycleCommodityPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {

        QueryCommodityParam queryCommodityParam = new QueryCommodityParam();
        queryCommodityParam.setPageNum(pageNum);
        queryCommodityParam.setPageSize(pageSize);

        BaseResult<BasePage<RecycleCommodityBO>> recycleCommodityPage = recycleCommodityService.getRecycleCommodityPage(queryCommodityParam);
        if (BaseResultUtil.isSuccess(recycleCommodityPage)) {
            return BaseResultUtil.success(buildCommodity(recycleCommodityPage.getData()));
        }

        return BaseResultUtil.fail(recycleCommodityPage.getMsg());
    }

    @RequestMapping("getCommodityDetail")
    public BaseResult<RecycleCommodityVO> getCommodityDetail(@RequestParam("commodityNo") String commodityNo) {
        if (StringUtils.isEmpty(commodityNo)) {
            return BaseResultUtil.fail("没有商品编号");
        }

        BaseResult<RecycleCommodityBO> baseResult = recycleCommodityService.getRecycleCommodity(commodityNo);
        if (BaseResultUtil.isSuccess(baseResult)) {
            RecycleCommodityBO recycleCommodityBO = baseResult.getData();
            RecycleCommodityVO recycleCommodityVO = buildCommodityVO(recycleCommodityBO);

            BaseResult<RecycleCollectorBO> collector = recycleCollectorService.getRecycleCollectorByNo(recycleCommodityBO.getCollectorNo());
            if (BaseResultUtil.isSuccess(collector)) {
                recycleCommodityVO.setCollectorName(collector.getData().getUsername());
            }

            return BaseResultUtil.success(recycleCommodityVO);
        }

        return BaseResultUtil.fail(baseResult.getMsg());
    }


    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public BaseResult<Boolean> insertRecycleCommodity(@RequestBody CommodityParam commodityParam) {

        RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();
        BeanUtils.copyProperties(commodityParam, recycleCommodityBO);

        if (!CollectionUtils.isEmpty(commodityParam.getImgUrl())) {
            recycleCommodityBO.setImgUrl(commodityParam.getImgUrl().stream().reduce((item1, item2) -> item1 + "," + item2).orElse(null));
        }

        BaseResult<Integer> baseResult = recycleCommodityService.insertRecycleCommodity(recycleCommodityBO);

        if (BaseResultUtil.isSuccess(baseResult)) {
            return BaseResultUtil.success(true);
        }

        return BaseResultUtil.fail(baseResult.getMsg());
    }

    @RequestMapping(value = "updateCommodityToStart", method = RequestMethod.POST)
    public BaseResult<Integer> updateCommodityToStart(@RequestBody CommodityParam commodityParam) {
        return recycleCommodityService.updateCommodityToStart(commodityParam.getCommodityNo());
    }

    @RequestMapping(value = "updateCommodityToStop", method = RequestMethod.POST)
    public BaseResult<Integer> updateCommodityToStop(@RequestBody CommodityParam commodityParam) {
        return recycleCommodityService.updateCommodityToStop(commodityParam.getCommodityNo());
    }

    private BasePage<RecycleCommodityVO> buildCommodity(BasePage<RecycleCommodityBO> basePage) {
        Map<String, String> collectors = Maps.newHashMap();
        BaseResult<List<RecycleCollectorBO>> allRecycleCollector = recycleCollectorService.getAllRecycleCollector();
        if (BaseResultUtil.isSuccess(allRecycleCollector) && !CollectionUtils.isEmpty(allRecycleCollector.getData())) {
            allRecycleCollector.getData().forEach(recycleCollectorBO -> collectors.put(recycleCollectorBO.getCollectorNo(), recycleCollectorBO.getUsername()));
        }

        List<RecycleCommodityVO> recycleCommodityVOS = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(basePage.getList())) {
            basePage.getList().forEach(recycleCommodityBO -> {
                RecycleCommodityVO recycleCommodityVO = buildCommodityVO(recycleCommodityBO);
                recycleCommodityVO.setCollectorName(collectors.get(recycleCommodityBO.getCollectorNo()));

                recycleCommodityVOS.add(recycleCommodityVO);
            });
        }

        return BasePageUtil.successPage(basePage.getPageNum(), basePage.getPageSize(), basePage.getCount(), recycleCommodityVOS);
    }

    private RecycleCommodityVO buildCommodityVO(RecycleCommodityBO recycleCommodityBO) {
        RecycleCommodityVO recycleCommodityVO = new RecycleCommodityVO();
        BeanUtils.copyProperties(recycleCommodityBO, recycleCommodityVO);
        if (!ObjectUtils.isEmpty(recycleCommodityBO.getRecycleTime())) {
            recycleCommodityVO.setRecycleTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD, recycleCommodityBO.getRecycleTime()));
        }
        if (!ObjectUtils.isEmpty(recycleCommodityBO.getImgUrl())) {
            recycleCommodityVO.setImgUrl(Lists.newArrayList(recycleCommodityBO.getImgUrl().split(",")));
        }
        recycleCommodityVO.setCommodityStatusDesc(CommodityStatus.getCommodityStatusDesc(recycleCommodityBO.getCommodityStatus()));

        return recycleCommodityVO;
    }
}
