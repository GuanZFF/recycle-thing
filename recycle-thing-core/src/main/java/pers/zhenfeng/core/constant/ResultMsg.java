package pers.zhenfeng.core.constant;

public enum ResultMsg {
    SUCCESS("success"), FAIL("fail"), FALLBACK("熔断异常"), PARAM_ERROR("参数错误，不符合规则或者存在空");

    private String msg;

    ResultMsg(String code) {
        this.msg = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
