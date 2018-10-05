package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.fallback.RecycleCommodityServiceFallback;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;

@FeignClient(name = "recycle-thing-service", fallback = RecycleCommodityServiceFallback.class)
@Component
public interface RecycleCommodityService {

    @RequestMapping("/commodity/getRecycleCommodity")
    BaseResult<RecycleCommodityBO> getRecycleCommodity(@RequestParam("commodityNo") String commodityNo);

    @RequestMapping(value = "/commodity/getRecycleCommodityPage", method = RequestMethod.POST)
    BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestBody QueryCommodityParam param);

    @RequestMapping(value = "/commodity/insert", method = RequestMethod.POST)
    BaseResult<Integer> insertRecycleCommodity(@RequestBody RecycleCommodityBO recycleCommodityBO);

    @RequestMapping("/commodity/updateCommodityToStop")
    BaseResult<Integer> updateCommodityToStop(@RequestParam("commodityNo") String commodityNo);

    @RequestMapping("/commodity/updateCommodityToStart")
    BaseResult<Integer> updateCommodityToStart(@RequestParam("commodityNo") String commodityNo);
}
