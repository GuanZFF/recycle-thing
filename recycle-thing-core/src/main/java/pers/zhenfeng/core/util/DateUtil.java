package pers.zhenfeng.core.util;

import java.text.ParseException;
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
        if (format == null || time == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }


    public static Date getStringToDate(String time) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getStringToDate(String format, String time) {
        if (format == null || time == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
}
