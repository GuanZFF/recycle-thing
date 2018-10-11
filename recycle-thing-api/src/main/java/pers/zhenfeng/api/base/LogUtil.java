package pers.zhenfeng.api.base;

import pers.zhenfeng.api.bo.RecycleLogBO;
import pers.zhenfeng.core.constant.LogType;

/**
 * @author Grow-Worm
 * @date 2018/10/11
 */
public class LogUtil {
    public static RecycleLogBO error(String title, String context) {
        RecycleLogBO recycleLogPO = new RecycleLogBO();
        recycleLogPO.setType(LogType.ERROR.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        return recycleLogPO;
    }

    public static RecycleLogBO error(String title, String message, String context) {
        RecycleLogBO recycleLogPO = new RecycleLogBO();
        recycleLogPO.setType(LogType.ERROR.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        recycleLogPO.setMessage(message);
        return recycleLogPO;
    }

    public static RecycleLogBO info(String title, String context) {
        RecycleLogBO recycleLogPO = new RecycleLogBO();
        recycleLogPO.setType(LogType.INFO.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        return recycleLogPO;
    }

    public static RecycleLogBO info(String title, String message, String context) {
        RecycleLogBO recycleLogPO = new RecycleLogBO();
        recycleLogPO.setType(LogType.INFO.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        recycleLogPO.setMessage(message);
        return recycleLogPO;
    }
}
