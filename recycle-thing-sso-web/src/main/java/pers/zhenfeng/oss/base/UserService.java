package pers.zhenfeng.oss.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Grow-Worm
 * @date 2018/11/09
 */
@Component
public class UserService implements UserDetailsService {

    @Resource
    private RecycleService recycleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        BaseResult<SsoUserBO> result = recycleService.loadSsoUserByUsername(s);
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
