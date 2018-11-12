package pers.zhenfeng.service.po;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
public class SsoRolePO extends BasePO {

    private Integer id;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
