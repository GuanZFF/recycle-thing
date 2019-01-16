package pers.zhenfeng.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.zhenfeng.user.po.RecycleUserPO;

/**
 * @author Grow-Worm
 * @date 2018/10/28
 */
@Component
@Mapper
public interface RecycleUserMapper {

    /**
     * 根据用户编号获取用户信息
     *
     * @param userNo 用户编号
     *
     * @return 用户信息
     */
    RecycleUserPO getUserByNo(@Param("userNo") String userNo);

    /**
     * 插入用户信息
     *
     * @param recycleUserPO 用户信息
     *
     * @return 插入结果
     */
    Integer insert(RecycleUserPO recycleUserPO);
}
