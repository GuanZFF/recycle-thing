package pers.zhenfeng.core.constant;

/**
 * @author Grow-Worm
 * @date 2019/01/12
 */
public interface RedisKeyPrefix {

    /**
     * 分割符号
     */
    String separatorChar = "-";

    /**
     * 收集人信息redis key前缀
     */
    String recycleCollector = "RECYCLE-COLLECTOR" + separatorChar;
}
