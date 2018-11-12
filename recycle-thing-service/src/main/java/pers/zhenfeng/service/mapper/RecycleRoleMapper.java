package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.SsoRolePO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/10
 */
@Component
@Mapper
public interface RecycleRoleMapper {

    List<SsoRolePO> getRoleById(@Param("id") Integer id);

}
