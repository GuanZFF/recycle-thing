package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.RecycleCollectorPO;

@Component
@Mapper
public interface RecycleCollectorMapper {

    RecycleCollectorPO getRecycleCollector(@Param("id") Integer id);

}
