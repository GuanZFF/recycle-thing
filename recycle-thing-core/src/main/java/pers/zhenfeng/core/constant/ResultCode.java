package pers.zhenfeng.core.constant;

/**
 * 代码CODE解释100101(100下面的CODE不够时用于升级，10业务模块，1业务模块代码)
 */
public enum  ResultCode {
    SUCCESS(200), UNAUTH(403), FAIL(500), FALLBACK(100101), LOGIN(100001);

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
