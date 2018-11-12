package pers.zhenfeng.api.bo;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/11/09
 */
public class SsoUserBO {

    private Integer id;

    private String username;

    private String password;

    private String iphone;

    private Integer sex;

    private List<SsoRoleBO> ssoRoleBOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public List<SsoRoleBO> getSsoRoleBOS() {
        return ssoRoleBOS;
    }

    public void setSsoRoleBOS(List<SsoRoleBO> ssoRoleBOS) {
        this.ssoRoleBOS = ssoRoleBOS;
    }
}
