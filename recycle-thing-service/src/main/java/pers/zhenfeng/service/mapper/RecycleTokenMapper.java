package pers.zhenfeng.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.service.po.SsoTokenPO;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@Component
@Mapper
public interface RecycleTokenMapper {

    SsoTokenPO getTokenBySeries(@Param("series") String series);

    Integer insert(SsoTokenPO ssoTokenPO);

}
