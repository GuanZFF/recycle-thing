package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.mapper.RecycleCommodityMapper;
import pers.zhenfeng.service.po.RecycleCommodityPO;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/commodity")
public class RecycleCommodityController {

    @Resource
    private RecycleCommodityMapper recycleCommodityMapper;

    @RequestMapping("getRecycleCommodity")
    public BaseResult<RecycleCommodityBO> getRecycleCommodity(@RequestParam("id") Integer id) {
        RecycleCommodityPO recycleCommodityPO = recycleCommodityMapper.getRecycleCommodity(id);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.success();
        }

        RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCommodityBO);

        return BaseResultUtil.success(recycleCommodityBO);
    }

    @RequestMapping("getRecycleCommodityPage")
    public BaseResult<BasePage<RecycleCommodityBO>> getRecycleCommodityPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Integer index = (pageNum - 1) * pageSize;

        Integer count = recycleCommodityMapper.getRecycleCommodityPageCount();
        if (count <= 0) {
            return BaseResultUtil.success(BasePageUtil.emptyPage());
        }

        List<RecycleCommodityPO> list = recycleCommodityMapper.getRecycleCommodityPage(index, pageSize);

        List<RecycleCommodityBO> recycleCommodityBOS = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return BaseResultUtil.success(BasePageUtil.successPage(pageNum, pageSize, count, recycleCommodityBOS));
        }

        BeanUtils.copyProperties(list, recycleCommodityBOS);
        list.forEach(item -> {
            RecycleCommodityBO recycleCommodityBO = new RecycleCommodityBO();
            BeanUtils.copyProperties(item, recycleCommodityBO);
            recycleCommodityBOS.add(recycleCommodityBO);
        });

        BasePage<RecycleCommodityBO> basePage = BasePageUtil.successPage(pageNum, pageSize, count, recycleCommodityBOS);

        return BaseResultUtil.success(basePage);
    }

}
