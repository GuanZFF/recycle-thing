package pers.zhenfeng.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Grow-Worm
 * @date 2018/09/25
 */
public class DateUtil {

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String getDateString(String format, Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }
}
