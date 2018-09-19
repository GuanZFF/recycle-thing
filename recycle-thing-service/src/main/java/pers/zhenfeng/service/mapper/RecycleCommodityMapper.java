package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.RecycleCommodityPO;

import java.util.List;

@Component
@Mapper
public interface RecycleCommodityMapper {

    RecycleCommodityPO getRecycleCommodity(@Param("id") Integer id);

    List<RecycleCommodityPO> getRecycleCommodityPage(@Param("index") Integer index, @Param("size") Integer size);

    Integer getRecycleCommodityPageCount();
}
