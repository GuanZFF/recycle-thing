package pers.zhenfeng.core.util;

import java.security.MessageDigest;

/**
 * @author Grow-Worm
 * @date 2018/11/13
 */
public class EncodeUtil {

    public static String md5(String src) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(src.getBytes());
        byte b[] = md5.digest();

        int i;

        StringBuilder buf = new StringBuilder();
        for (byte aB : b) {
            i = aB;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        // 32位加密
        return buf.toString().toUpperCase();
        // 16位的加密
//        return buf.toString().substring(8, 24).toUpperCase();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(md5("123"));
    }

}
