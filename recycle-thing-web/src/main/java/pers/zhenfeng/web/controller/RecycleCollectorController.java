package pers.zhenfeng.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.api.service.RecycleCollectorService;
import pers.zhenfeng.core.base.BaseResult;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/09/19
 */
@RestController
@RequestMapping("/collector")
public class RecycleCollectorController {

    @Resource
    private RecycleCollectorService recycleCollectorService;

    @RequestMapping("getRecycleCollector")
    public BaseResult<RecycleCollectorBO> getRecycleCollector(@RequestParam("id") Integer id) {
        return recycleCollectorService.getRecycleCollector(id);
    }

}
