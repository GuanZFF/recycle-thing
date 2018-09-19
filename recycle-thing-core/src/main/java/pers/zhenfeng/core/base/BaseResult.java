package pers.zhenfeng.core.base;

public class BaseResult<E> {
    private int code;
    private String msg;
    private E data;

    public BaseResult() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }
}