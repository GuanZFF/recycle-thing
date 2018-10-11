package pers.zhenfeng.service.service;

import pers.zhenfeng.service.po.RecycleLogPO;

import java.util.List;

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


    /**
     * 插入日志
     *
     * @param recycleLogPOS 日志内容
     */
    void insertRecycleLog(List<RecycleLogPO> recycleLogPOS);

}
