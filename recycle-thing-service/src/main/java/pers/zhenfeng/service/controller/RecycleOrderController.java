package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.core.base.BaseResult;
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

}
