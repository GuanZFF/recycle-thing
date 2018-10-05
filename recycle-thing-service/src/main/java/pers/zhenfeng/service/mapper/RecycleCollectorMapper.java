package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.RecycleCollectorPO;

import java.util.List;

@Component
@Mapper
public interface RecycleCollectorMapper {

    RecycleCollectorPO getRecycleCollector(@Param("id") Integer id);

    RecycleCollectorPO getRecycleCollectorByNo(@Param("collectorNo") String collectorNo);

    List<RecycleCollectorPO> getAllRecycleCollector();

}
