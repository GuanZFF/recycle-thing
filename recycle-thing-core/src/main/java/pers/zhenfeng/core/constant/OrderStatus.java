package pers.zhenfeng.core.constant;

/**
 * @author Grow-Worm
 * @date 2018/12/28
 */
public enum OrderStatus {
    CREATE_ORDER(1, "error"), COMPLETE_ORDER(2, "info");

    private Integer code;

    private String msg;

    OrderStatus(Integer code, String msg) {
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
