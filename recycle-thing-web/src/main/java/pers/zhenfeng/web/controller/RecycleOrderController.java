package pers.zhenfeng.web.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.api.service.RecycleOrderService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.web.vo.RecycleOrderVO;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
@RestController
@RequestMapping("/order")
public class RecycleOrderController {

    @Resource
    private RecycleOrderService recycleOrderService;

    /**
     * 插入订单数据
     *
     * @param recycleOrderVO 订单信息
     *
     * @return 插入结果
     */
    @RequestMapping("insert")
    public BaseResult<Integer> insert(@RequestBody RecycleOrderVO recycleOrderVO) {
        RecycleOrderBO recycleOrderBO = new RecycleOrderBO();

        BeanUtils.copyProperties(recycleOrderVO, recycleOrderBO);

        return recycleOrderService.insert(recycleOrderBO);
    }

    /**
     * 通过用户ID获取订单列表
     *
     * @param uid 用户唯一ID
     *
     * @return 订单列表
     */
    @RequestMapping("getRecycleOrderList")
    public BaseResult<List<RecycleOrderVO>> getRecycleOrderList(@RequestParam("uid") String uid) {
        if (StringUtils.isEmpty(uid)) {
            return BaseResultUtil.emptyList();
        }

        // 读取服务订单数据
        BaseResult<List<RecycleOrderBO>> result = recycleOrderService.getRecycleOrderList(uid);
        if (BaseResultUtil.isFail(result)) {
            return BaseResultUtil.fail(result.getMsg());
        }

        // 判断订单是否为空
        List<RecycleOrderBO> recycleOrderBOS = result.getData();
        if (CollectionUtils.isEmpty(recycleOrderBOS)) {
            return BaseResultUtil.success(Collections.emptyList());
        }

        List<RecycleOrderVO> recycleOrderVOS = Lists.newArrayList();

        // COPY订单数据
        recycleOrderBOS.forEach(recycleOrderBO -> {
            RecycleOrderVO recycleOrderVO = new RecycleOrderVO();
            recycleOrderVOS.add(recycleOrderVO);

            BeanUtils.copyProperties(recycleOrderBO, recycleOrderVO);
        });

        return BaseResultUtil.success(recycleOrderVOS);
    }

}
