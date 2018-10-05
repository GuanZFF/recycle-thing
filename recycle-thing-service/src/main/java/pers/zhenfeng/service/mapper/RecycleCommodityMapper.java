package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.QueryCommodityParam;
import pers.zhenfeng.service.po.RecycleCommodityPO;

import java.util.List;

@Component
@Mapper
public interface RecycleCommodityMapper {

    RecycleCommodityPO getRecycleCommodity(@Param("id") Integer id);

    RecycleCommodityPO getRecycleCommodityByNo(@Param("commodityNo") String commodityNo);

    List<RecycleCommodityPO> getRecycleCommodityPage(@Param("index") Integer index, @Param("size") Integer size,
                                                     @Param("param") QueryCommodityParam param);

    Integer getRecycleCommodityPageCount(@Param("param") QueryCommodityParam param);

    Integer insert(RecycleCommodityPO recycleCommodityPO);

    Integer updateCommodityToStart(@Param("commodityNo") String commodityNo);

    Integer updateCommodityToStop(@Param("commodityNo") String commodityNo);
}
