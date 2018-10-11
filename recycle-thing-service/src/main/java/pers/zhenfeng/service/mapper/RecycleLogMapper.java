package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.RecycleLogPO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/03
 */
@Component
@Mapper
public interface RecycleLogMapper {

    Integer insert(@Param("recycleLogPO") RecycleLogPO recycleLogPO);

    Integer batchInsert(@Param("list") List<RecycleLogPO> recycleLogPOS);

}
