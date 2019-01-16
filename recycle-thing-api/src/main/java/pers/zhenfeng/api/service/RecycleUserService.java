package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.RecycleUserBO;
import pers.zhenfeng.api.fallback.RecycleTokenServiceFallback;
import pers.zhenfeng.core.base.BaseResult;

/**
 * @author Grow-Worm
 * @date 2019/01/16
 */
@FeignClient(name = "recycle-thing-user", fallback = RecycleTokenServiceFallback.class)
@Component
public interface RecycleUserService {

    @RequestMapping("/user/getUserByNo")
    BaseResult<RecycleUserBO> getUserByNo(@RequestParam("userNo") String userNo);

    @RequestMapping("/insert")
    BaseResult<RecycleUserBO> insert(@RequestBody RecycleUserBO recycleUserBO);

}
