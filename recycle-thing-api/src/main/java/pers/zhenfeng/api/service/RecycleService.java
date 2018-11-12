package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.SsoUserBO;
import pers.zhenfeng.api.fallback.RecycleServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

/**
 * @author Grow-Worm
 * @date 2018/11/09
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleServiceFallback.class)
@Component
public interface RecycleService {

    @RequestMapping("/recycle/getUserByUsername")
    BaseResult<SsoUserBO> loadSsoUserByUsername(@RequestParam("username") String username);

}
