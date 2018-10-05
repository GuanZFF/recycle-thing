package pers.zhenfeng.service.po;

import javax.xml.crypto.Data;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
public class BasePO {
    private Integer isDelete;

    private String createBy;

    private Data createTime;

    private String updateBy;

    private Data updateTime;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Data getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Data updateTime) {
        this.updateTime = updateTime;
    }
}
