package pers.zhenfeng.service.utils;

import com.google.common.collect.Lists;
import pers.zhenfeng.core.constant.LogType;
import pers.zhenfeng.service.po.RecycleLogPO;

import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/10
 */
public class LogUtil {

    public static List<RecycleLogPO> recycleLogPOS = Lists.newArrayList();

    public static void insertLog(RecycleLogPO recycleLogPO) {
        recycleLogPOS.add(recycleLogPO);
    }

    public static void error(String title, String context) {
        RecycleLogPO recycleLogPO = new RecycleLogPO();
        recycleLogPO.setType(LogType.ERROR.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);

        recycleLogPOS.add(recycleLogPO);
    }

    public static void error(String title, String message, String context) {
        RecycleLogPO recycleLogPO = new RecycleLogPO();
        recycleLogPO.setType(LogType.ERROR.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        recycleLogPO.setMessage(message);

        recycleLogPOS.add(recycleLogPO);
    }

    public static void info(String title, String context) {
        RecycleLogPO recycleLogPO = new RecycleLogPO();
        recycleLogPO.setType(LogType.INFO.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);

        recycleLogPOS.add(recycleLogPO);
    }

    public static void info(String title, String message, String context) {
        RecycleLogPO recycleLogPO = new RecycleLogPO();
        recycleLogPO.setType(LogType.INFO.getCode());
        recycleLogPO.setTitle(title);
        recycleLogPO.setText(context);
        recycleLogPO.setMessage(message);

        recycleLogPOS.add(recycleLogPO);
    }
}
