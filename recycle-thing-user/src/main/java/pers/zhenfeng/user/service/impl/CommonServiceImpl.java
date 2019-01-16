package pers.zhenfeng.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import pers.zhenfeng.user.mapper.NumberManageMapper;
import pers.zhenfeng.user.po.NumberManagePO;
import pers.zhenfeng.user.service.CommonService;

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

    public Integer getNumber(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0;
        }
        NumberManagePO numberManagePO = numberManageMapper.getNumberManage(key);
        if (ObjectUtils.isEmpty(numberManagePO)) {
            return 0;
        }
        Integer value = numberManagePO.getValue() + (int) (Math.random() * 10);
        numberManageMapper.updateNumberManage(key, value);
        return value;
    }
}
