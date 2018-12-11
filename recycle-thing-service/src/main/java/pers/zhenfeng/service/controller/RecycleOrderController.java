package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.service.constant.NumberManage;
import pers.zhenfeng.service.mapper.RecycleOrderMapper;
import pers.zhenfeng.service.po.RecycleOrderPO;
import pers.zhenfeng.service.service.CommonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/28
 */
@RestController
@RequestMapping("order")
public class RecycleOrderController {

    @Resource
    private RecycleOrderMapper recycleOrderMapper;

    @Resource
    private CommonService commonService;

    /**
     * 插入订单数据
     *
     * @param recycleOrderBO 订单信息
     *
     * @return 插入结果
     */
    @RequestMapping("insert")
    public BaseResult<Integer> insert(@RequestBody RecycleOrderBO recycleOrderBO) {
        RecycleOrderPO recycleOrderPO = new RecycleOrderPO();
        BeanUtils.copyProperties(recycleOrderBO, recycleOrderPO);

        // 生成商品编号
        String orderNo = NumberUtil.generateOrderNo(commonService.getNumber(NumberManage.ORDER.getKey()));

        recycleOrderPO.setOrderNo(orderNo);

        // 插入结果
        Integer id = recycleOrderMapper.insert(recycleOrderPO);

        return BaseResultUtil.success(id);
    }

    /**
     * 通过用户ID获取订单列表
     *
     * @param uid 用户唯一ID
     *
     * @return 订单列表
     */
    @RequestMapping("getRecycleOrderList")
    public BaseResult<List<RecycleOrderBO>> getRecycleOrderList(@RequestParam("uid") String uid) {
        if (StringUtils.isEmpty(uid)) {
            return BaseResultUtil.failParam();
        }

        // 读取订单列表
        List<RecycleOrderPO> recycleOrderPOS = recycleOrderMapper.getRecycleOrderList(uid);
        if (CollectionUtils.isEmpty(recycleOrderPOS)) {
            return BaseResultUtil.emptyList();
        }

        List<RecycleOrderBO> recycleOrderBOS = Lists.newArrayList();

        // COPY属性
        recycleOrderPOS.forEach(recycleOrderPO -> {
            RecycleOrderBO recycleOrderBO = new RecycleOrderBO();
            recycleOrderBOS.add(recycleOrderBO);

            BeanUtils.copyProperties(recycleOrderPO, recycleOrderBO);
        });

        return BaseResultUtil.success(recycleOrderBOS);
    }

    /**
     * 分页获取订单信息
     *
     * @param param 查询参数
     *
     * @return 订单结果
     */
    @RequestMapping(value = "getRecycleOrderPage", method = RequestMethod.POST)
    public BaseResult<BasePage<RecycleOrderBO>> getRecycleOrderPage(@RequestBody QueryOrderParam param) {
        Integer index = (param.getPageNum() - 1) * param.getPageSize();

        Integer count = recycleOrderMapper.getRecycleOrderPageCount(param);
        if (count == null || count == 0) {
            return BaseResultUtil.success(BasePageUtil.emptyPage());
        }

        List<RecycleOrderPO> list = recycleOrderMapper.getRecycleOrderPage(index, param.getPageSize(), param);

        List<RecycleOrderBO> recycleOrderBOS = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return BaseResultUtil.success(BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleOrderBOS));
        }

        BeanUtils.copyProperties(list, recycleOrderBOS);
        list.forEach(item -> {
            RecycleOrderBO recycleOrderBO = new RecycleOrderBO();
            BeanUtils.copyProperties(item, recycleOrderBO);
            recycleOrderBOS.add(recycleOrderBO);
        });

        BasePage<RecycleOrderBO> basePage = BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleOrderBOS);

        return BaseResultUtil.success(basePage);
    }

}
