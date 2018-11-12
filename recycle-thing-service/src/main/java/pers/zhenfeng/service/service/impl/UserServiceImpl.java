package pers.zhenfeng.service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pers.zhenfeng.service.mapper.RecycleRoleMapper;
import pers.zhenfeng.service.mapper.RecycleUserMapper;
import pers.zhenfeng.service.mapper.RecycleUserRoleMapper;
import pers.zhenfeng.service.po.SsoRolePO;
import pers.zhenfeng.service.po.SsoUserPO;
import pers.zhenfeng.service.po.SsoUserRolePO;
import pers.zhenfeng.service.service.UserService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RecycleUserMapper recycleUserMapper;

    @Resource
    private RecycleRoleMapper recycleRoleMapper;

    @Resource
    private RecycleUserRoleMapper recycleUserRoleMapper;

    @Override
    public SsoUserPO getUserByUsername(String username) {
        return recycleUserMapper.getUserByUsername(username);
    }

    @Override
    public List<SsoRolePO> getRoleList(Integer userId) {
        SsoUserRolePO userRolePO = recycleUserRoleMapper.getUserRoleByUserId(userId);
        if (ObjectUtils.isEmpty(userRolePO)) {
            return Collections.emptyList();
        }

        return recycleRoleMapper.getRoleById(userRolePO.getRoleId());
    }
}
