package pers.zhenfeng.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.api.service.RecycleCommodityService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/09/18
 */
@RestController
@RequestMapping("/commodity")
public class RecycleCommodityController {

    @Resource
    private RecycleCommodityService recycleCommodityService;

    @RequestMapping("/getRecycleCommodity")
    public BaseResult<RecycleCommodityBO> getRecycleCommodity(Integer id) {
        return recycleCommodityService.getRecycleCommodity(id);
    }

    @RequestMapping("getRecycleCommodityPage")
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return recycleCommodityService.getRecycleCommodityPage(pageNum, pageSize);
    }
}
