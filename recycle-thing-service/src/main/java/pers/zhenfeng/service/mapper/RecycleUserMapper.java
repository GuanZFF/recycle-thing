package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.SsoUserPO;

/**
 * @author Grow-Worm
 * @date 2018/10/28
 */
@Component
@Mapper
public interface RecycleUserMapper {

    SsoUserPO getUserByUsername(@Param("username") String username);

}
