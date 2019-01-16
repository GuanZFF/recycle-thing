package pers.zhenfeng.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.user.po.NumberManagePO;

/**
 * @author Grow-Worm
 * @date 2018/09/26
 */
@Component
@Mapper
public interface NumberManageMapper {

    NumberManagePO getNumberManage(@Param("key") String key);

    Integer updateNumberManage(@Param("key") String key, @Param("value") Integer value);

}
