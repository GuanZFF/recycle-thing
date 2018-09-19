package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.fallback.RecycleCollectorServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleCollectorServiceFallback.class)
@Component
public interface RecycleCollectorService {

    @RequestMapping("/collector/getRecycleCollector")
    BaseResult<RecycleCollectorBO> getRecycleCollector(@RequestParam("id") Integer id);
}
