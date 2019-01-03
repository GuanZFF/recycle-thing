package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.zhenfeng.api.bo.QueryReverseOrderParam;
import pers.zhenfeng.api.bo.RecycleReverseOrderBO;
import pers.zhenfeng.api.fallback.RecycleReverseOrderServiceFallback;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2019/01/02
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleReverseOrderServiceFallback.class)
@Component
public interface RecycleReverseOrderService {

    @RequestMapping("/reverse/insert")
    BaseResult<Integer> insert(@RequestBody RecycleReverseOrderBO recycleReverseOrderBO);

    @RequestMapping("/reverse/getReverseOrders")
    BaseResult<List<RecycleReverseOrderBO>> getReverseOrders(String uid);

    @RequestMapping(value = "/reverse/getReverseOrderPage", method = RequestMethod.POST)
    BaseResult<BasePage<RecycleReverseOrderBO>> getReverseOrderPage(@RequestBody QueryReverseOrderParam param);

}
