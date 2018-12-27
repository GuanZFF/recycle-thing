package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.bo.RecycleLogBO;
import pers.zhenfeng.api.fallback.CommonServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/05
 */
@FeignClient(name = "recycle-thing-service", fallback = CommonServiceFallback.class)
@Component
public interface CommonService {

    @RequestMapping("/common/getAllCommodityType")
    BaseResult<List<CommodityTypeBO>> getAllCommodityType();

    @RequestMapping("/common/getCommodityType")
    BaseResult<CommodityTypeBO> getCommodityType(@RequestParam("id") Integer id);

    @RequestMapping("/common/insertCommodityType")
    BaseResult<Integer> insertCommodityType(@RequestBody CommodityTypeBO commodityTypeBO);

    @RequestMapping("/common/deleteCommodityType")
    BaseResult<Integer> deleteCommodityType(@RequestParam("id") Integer id);

    @RequestMapping("/common/insertLog")
    BaseResult<Void> insertLog(@RequestBody RecycleLogBO recycleLogBO);
}
