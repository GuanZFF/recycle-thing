package pers.zhenfeng.core.util;

import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.ResultCode;
import pers.zhenfeng.core.constant.ResultMsg;

public class BaseResultUtil {

    public static Boolean isSuccess(BaseResult baseResult) {
        return baseResult.getCode() == ResultCode.SUCCESS.getCode();
    }

    public static Boolean isFail(BaseResult baseResult) {
        return !isSuccess(baseResult);
    }

    public static <T> BaseResult<T> success(T e) {
        BaseResult<T> tTmsBaseResult = new BaseResult<>();
        tTmsBaseResult.setCode(ResultCode.SUCCESS.getCode());
        tTmsBaseResult.setMsg(ResultMsg.SUCCESS.getMsg());
        tTmsBaseResult.setData(e);
        return tTmsBaseResult;
    }

    public static <T> BaseResult<T> success() {
        BaseResult<T> tTmsBaseResult = new BaseResult<>();
        tTmsBaseResult.setCode(ResultCode.SUCCESS.getCode());
        tTmsBaseResult.setMsg(ResultMsg.SUCCESS.getMsg());
        return tTmsBaseResult;
    }

    public static <T> BaseResult<T> fail(String resultDesc) {
        return fail(ResultCode.FALLBACK.getCode(), resultDesc);
    }

    public static <T> BaseResult<T> fallback() {
        return fail(ResultCode.FALLBACK.getCode(), ResultMsg.FALLBACK.getMsg());
    }


    public static <T> BaseResult<T> fail(int resultCode, String resultDesc) {
        BaseResult<T> tTmsBaseResult = new BaseResult<>();
        tTmsBaseResult.setCode(resultCode);
        tTmsBaseResult.setMsg(resultDesc);
        return tTmsBaseResult;
    }

}
