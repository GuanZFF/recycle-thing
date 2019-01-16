package pers.zhenfeng.user.service;

/**
 * @author Grow-Worm
 * @date 2018/10/08
 */
public interface CommonService {

    /**
     * 生成递增随机数
     *
     * @param key 主键
     *
     * @return 随机数
     */
    Integer getNumber(String key);
}
