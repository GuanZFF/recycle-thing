package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import pers.zhenfeng.UserInfo;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT * FROM User WHERE id = #{id}")
    UserInfo findUserInfo(@Param("id") String id);

}
