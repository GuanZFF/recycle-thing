package pers.zhenfeng.service.service;

import pers.zhenfeng.service.po.SsoRolePO;
import pers.zhenfeng.service.po.SsoUserPO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
public interface UserService {

    SsoUserPO getUserByUsername(String username);

    List<SsoRolePO> getRoleList(Integer userId);

}
