package pers.zhenfeng.core.constant;

import com.google.common.collect.Lists;

/**
 * @author Grow-Worm
 * @date 2018/10/05
 */
public enum  CollectorStatus {
    INIT(0, "初始值"), START(1, "启动"), STOP(2, "停止");

    private Integer code;

    private String msg;

    CollectorStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getCollectorStatusDesc(Integer code) {
        if (code == null) {
            return null;
        }
        return Lists.newArrayList(CollectorStatus.values()).stream().filter(item -> item.getCode().equals(code)).map(CollectorStatus::getMsg).findFirst().orElse(null);
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
