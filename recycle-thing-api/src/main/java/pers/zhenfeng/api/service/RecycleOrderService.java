package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.api.fallback.RecycleOrderServiceFallback;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleOrderServiceFallback.class)
@Component
public interface RecycleOrderService {

    @RequestMapping("/order/insert")
    BaseResult<Integer> insert(@RequestBody RecycleOrderBO recycleOrderBO);

    @RequestMapping("/order/getRecycleOrderList")
    BaseResult<List<RecycleOrderBO>> getRecycleOrderList(@RequestParam("uid") String uid);

    @RequestMapping("/order/getRecycleOrder")
    BaseResult<RecycleOrderBO> getRecycleOrder(@RequestParam("orderNo") String orderNo);

    @RequestMapping(value = "/order/getRecycleOrderPage", method = RequestMethod.POST)
    BaseResult<BasePage<RecycleOrderBO>> getRecycleOrderPage(@RequestBody QueryOrderParam param);
}
