package pers.zhenfeng.core.constant;

/**
 * @author Grow-Worm
 * @date 2018/10/12
 */
public enum LogType {
    ERROR(1, "error"), INFO(2, "info");

    private Integer code;

    private String msg;

    LogType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
