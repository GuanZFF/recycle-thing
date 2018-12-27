package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.CommodityTypePO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
@Component
@Mapper
public interface CommodityTypeMapper {

    Integer insertCommodityType(CommodityTypePO commodityTypePO);

    List<CommodityTypePO> getAllCommodityType();

    CommodityTypePO getCommodityType(@Param("id") Integer id);

    Integer deleteCommodityType(@Param("id") Integer id);

}
