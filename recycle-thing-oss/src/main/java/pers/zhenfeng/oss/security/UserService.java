package pers.zhenfeng.oss.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.SsoRoleBO;
import pers.zhenfeng.api.bo.SsoUserBO;
import pers.zhenfeng.api.service.RecycleService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grow-Worm
 * @date 2018/11/09
 */
@Component
public class UserService implements UserDetailsService {

    private RecycleService recycleService;

    public UserService(RecycleService recycleService) {
        this.recycleService = recycleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BaseResult<SsoUserBO> result = recycleService.loadSsoUserByUsername(username);
        if (BaseResultUtil.isSuccess(result)) {
            SsoUserBO ssoUserBO = result.getData();
            List<String> roleList = ssoUserBO.getSsoRoleBOS().stream().map(SsoRoleBO::getRoleName).collect(Collectors.toList());

            String[] roles = new String[roleList.size()];
            roleList.toArray(roles);

            return User.withUsername(ssoUserBO.getUsername()).password(ssoUserBO.getPassword()).roles(roles).build();
        }

        return null;
    }

}
