package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.fallback.RecycleCollectorServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleCollectorServiceFallback.class)
@Component
public interface RecycleCollectorService {

    @RequestMapping("/collector/getRecycleCollector/{id}")
    BaseResult<RecycleCollectorBO> getRecycleCollector(@PathVariable("id") String id);

    @RequestMapping("/collector/getRecycleCollectorByNo")
    BaseResult<RecycleCollectorBO> getRecycleCollectorByNo(@RequestParam("collectorNo") String collectorNo);

    @RequestMapping("/collector/getAllRecycleCollector")
    BaseResult<List<RecycleCollectorBO>> getAllRecycleCollector();

    @RequestMapping("insertRecycleCollector")
    BaseResult<Integer> insertRecycleCollector(@RequestBody RecycleCollectorBO recycleCollectorBO);
}
