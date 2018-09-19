package pers.zhenfeng.core.constant;

public enum  ResultCode {
    SUCCESS(200), FAIL(500), FALLBACK(100101);

    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
