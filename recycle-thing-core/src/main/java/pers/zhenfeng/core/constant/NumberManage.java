package pers.zhenfeng.core.constant;

/**
 * @author Grow-Worm
 * @date 2018/09/26
 */
public enum NumberManage {

    COMMODITY("commodity"), COLLECTOR("collector"), ORDER("order"), REVERSE_ORDER("reverse_order"), USER("user");

    private String key;

    NumberManage(String key) {

        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
