package pers.zhenfeng.api.fallback;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import pers.zhenfeng.api.bo.SsoRoleBO;
import pers.zhenfeng.api.bo.SsoUserBO;
import pers.zhenfeng.api.service.RecycleService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/09
 */
@Component
public class RecycleServiceFallback implements RecycleService {

    @Override
    public BaseResult<SsoUserBO> loadSsoUserByUsername(String username) {
        SsoUserBO ssoUserBO = new SsoUserBO();
        ssoUserBO.setUsername("user");
        ssoUserBO.setPassword("password123");

        SsoRoleBO ssoRoleBO = new SsoRoleBO();
        ssoRoleBO.setRoleName("user");

        List<SsoRoleBO> ssoRoleBOList = Lists.newArrayList();
        ssoRoleBOList.add(ssoRoleBO);
        ssoUserBO.setSsoRoleBOS(ssoRoleBOList);

        return BaseResultUtil.success(ssoUserBO);
    }

}
