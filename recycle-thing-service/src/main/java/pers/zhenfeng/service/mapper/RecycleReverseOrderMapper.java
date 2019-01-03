package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.QueryReverseOrderParam;
import pers.zhenfeng.service.po.RecycleOrderPO;
import pers.zhenfeng.service.po.RecycleReverseOrderPO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/28
 */
@Component
@Mapper
public interface RecycleReverseOrderMapper {

    /**
     * 插入订单数据
     *
     * @param recycleReverseOrderPO 订单数据
     *
     * @return 插入结果
     */
    Integer insert(RecycleReverseOrderPO recycleReverseOrderPO);

    /**
     * 根据用户ID获取订单数据
     *
     * @param uid 用户ID
     *
     * @return 订单数据
     */
    List<RecycleReverseOrderPO> getReverseOrders(@Param("uid") String uid);

    /**
     * 分页获取订单数据
     *
     * @param index 分页起始值
     * @param size  分页大小
     * @param param 查询参数
     *
     * @return 查询的分页结果
     */
    List<RecycleReverseOrderPO> getReverseOrderPage(@Param("index") Integer index, @Param("size") Integer size,
                                                    @Param("param") QueryReverseOrderParam param);

    /**
     * 获取总数量
     *
     * @param param 查询条件
     *
     * @return 查询结果
     */
    Integer getReverseOrderPageCount(@Param("param") QueryReverseOrderParam param);

}
