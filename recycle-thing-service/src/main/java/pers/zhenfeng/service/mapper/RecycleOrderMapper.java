package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryOrderParam;
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
    Integer insert(@Param("recycleOrderPO") RecycleOrderPO recycleOrderPO);

    /**
     * 获取用户所有订单列表
     *
     * @param uid 用户唯一键
     *
     * @return 订单列表
     */
    List<RecycleOrderPO> getRecycleOrderList(@Param("uid") String uid);

    List<RecycleOrderPO> getRecycleOrderPage(@Param("index") Integer index, @Param("size") Integer size,
                                             @Param("param") QueryOrderParam param);

    Integer getRecycleOrderPageCount(@Param("param") QueryOrderParam param);
}
