package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.SsoRoleBO;
import pers.zhenfeng.api.bo.SsoUserBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.po.SsoRolePO;
import pers.zhenfeng.service.po.SsoUserPO;
import pers.zhenfeng.service.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
@RestController
@RequestMapping("recycle")
public class RecycleController {

    @Resource
    private UserService userService;

    @RequestMapping("getUserByUsername")
    public BaseResult<SsoUserBO> loadSsoUserByUsername(@RequestParam("username") String username) {
        // 参数校验
        if (StringUtils.isEmpty(username)) {
            return BaseResultUtil.failParam();
        }

        // 读取数据
        SsoUserPO userPO = userService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(userPO)) {
            return BaseResultUtil.fail("用户名不存在");
        }
        List<SsoRolePO> rolePOS = userService.getRoleList(userPO.getId());
        if (CollectionUtils.isEmpty(rolePOS)) {
            return BaseResultUtil.fail("用户没有角色配置");
        }

        // 数据封装
        SsoUserBO userBO = new SsoUserBO();
        BeanUtils.copyProperties(userPO, userBO);

        List<SsoRoleBO> roleBOS = Lists.newArrayList();
        rolePOS.forEach(ssoRolePO -> {
            SsoRoleBO ssoRoleBO = new SsoRoleBO();
            roleBOS.add(ssoRoleBO);

            BeanUtils.copyProperties(ssoRolePO, ssoRoleBO);
        });

        userBO.setSsoRoleBOS(roleBOS);

        return BaseResultUtil.success(userBO);
    }

}
