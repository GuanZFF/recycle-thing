package pers.zhenfeng.core.constant;

import com.google.common.collect.Lists;

/**
 * @author Grow-Worm
 * @date 2018/10/05
 */
public enum SexStatus {
    MAN(0, "男"), WOMAN(1, "女"), UNKNOWN(2, "未知");

    private Integer code;

    private String msg;

    SexStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getSexStatusDesc(Integer code) {
        if (code == null) {
            return null;
        }
        return Lists.newArrayList(SexStatus.values()).stream().filter(item -> item.getCode().equals(code)).map(SexStatus::getMsg).findFirst().orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
