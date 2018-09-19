package pers.zhenfeng.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.fallback.RecycleCommodityServiceFallback;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;

@FeignClient(name = "recycle-thing-service", fallback = RecycleCommodityServiceFallback.class)
@Component
public interface RecycleCommodityService {

    @RequestMapping("/commodity/getRecycleCommodity")
    BaseResult<RecycleCommodityBO> getRecycleCommodity(@RequestParam("id") Integer id);

    @RequestMapping("/commodity/getRecycleCommodityPage")
    BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

}
