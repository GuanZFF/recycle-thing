package pers.zhenfeng.service.service.impl;

import org.springframework.stereotype.Service;
import pers.zhenfeng.service.mapper.NumberManageMapper;
import pers.zhenfeng.service.mapper.RecycleLogMapper;
import pers.zhenfeng.service.po.NumberManagePO;
import pers.zhenfeng.service.po.RecycleLogPO;
import pers.zhenfeng.service.service.CommonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/08
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private NumberManageMapper numberManageMapper;

    @Resource
    private RecycleLogMapper recycleLogMapper;

    public Integer getNumber(String key) {
        NumberManagePO numberManagePO = numberManageMapper.getNumberManage(key);
        Integer value = numberManagePO.getValue() + (int) (Math.random() * 10);
        numberManageMapper.updateNumberManage(key, value);
        return value;
    }

    @Override
    public void insertRecycleLog(List<RecycleLogPO> recycleLogPOS) {
        recycleLogMapper.batchInsert(recycleLogPOS);
    }
}
