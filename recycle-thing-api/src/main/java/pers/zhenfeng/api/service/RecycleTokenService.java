package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.SsoTokenBO;
import pers.zhenfeng.api.fallback.RecycleTokenServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@FeignClient(name = "recycle-thing-service", fallback = RecycleTokenServiceFallback.class)
@Component
public interface RecycleTokenService {

    @RequestMapping("/token/getToken")
    BaseResult<SsoTokenBO> getTokenBySeries(@RequestParam("series") String series);

    @RequestMapping("/token/insert")
    BaseResult<Integer> insertToken(@RequestBody SsoTokenBO tokenBO);

}
