package pers.zhenfeng.core.util;

/**
 * @author Grow-Worm
 * @date 2018/09/25
 */
public class NumberUtil {

    /**
     * 生成商品编号头
     */
    private static String COMMODITY_HEADER = "RCN";

    /**
     * 生成收集人编号头
     */
    private static String COLLECTOR_HEADER = "RCR";

    /**
     * 生成收集人编号头
     */
    private static String ORDER_HEADER = "RCO";

    /**
     * 生成图片头信息
     */
    private static String IMAGE_HEADER = "RCI";

    private static String COMMODITY_BUSINESS_NO = "00001001";

    private static String COLLECTOR_BUSINESS_NO = "00001001";

    private static String ORDER_BUSINESS_NO = "00001001";

    private static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateCommodityNo(Integer no) {
        char first = CHARS.charAt((int) (Math.random() * 26));
        char second = CHARS.charAt((int) (Math.random() * 26));
        return COMMODITY_HEADER + COMMODITY_BUSINESS_NO + no + first + second;
    }


    public static String generateCollectorNo(Integer no) {
        char first = CHARS.charAt((int) (Math.random() * 26));
        char second = CHARS.charAt((int) (Math.random() * 26));
        return COLLECTOR_HEADER + COLLECTOR_BUSINESS_NO + no + first + second;
    }

    public static String generateOrderNo(Integer no) {
        char first = CHARS.charAt((int) (Math.random() * 26));
        char second = CHARS.charAt((int) (Math.random() * 26));
        return ORDER_HEADER + ORDER_BUSINESS_NO + no + first + second;
    }

    public static String generateImageName() {
        char first = CHARS.charAt((int) (Math.random() * 26));
        char second = CHARS.charAt((int) (Math.random() * 26));
        long currentTimeMillis = System.currentTimeMillis();

        return IMAGE_HEADER + String.valueOf(currentTimeMillis) + first + second;
    }

    public static void main(String[] args) {
        System.out.println(generateImageName());
    }

}
