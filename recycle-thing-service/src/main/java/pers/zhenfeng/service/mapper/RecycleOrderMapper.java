package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.service.po.RecycleCollectorPO;
import pers.zhenfeng.service.po.RecycleOrderPO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/28
 */
@Component
@Mapper
public interface RecycleOrderMapper {

    /**
     * 插入订单信息
     *
     * @param recycleOrderPO 订单信息
     *
     * @return 插入结果
     */
    Integer insert(RecycleOrderPO recycleOrderPO);

    /**
     * 获取用户所有订单列表
     *
     * @param uid 用户唯一键
     *
     * @return 订单列表
     */
    List<RecycleOrderPO> getRecycleOrderList(@Param("uid") String uid);

    /**
     * 根据订单号获取订单
     *
     * @param orderNo 订单号
     *
     * @return 订单信息
     */
    RecycleOrderPO getRecycleOrder(@Param("orderNo") String orderNo);

    List<RecycleOrderPO> getRecycleOrderPage(@Param("index") Integer index, @Param("size") Integer size,
                                             @Param("param") QueryOrderParam param);

    Integer getRecycleOrderPageCount(@Param("param") QueryOrderParam param);
}
