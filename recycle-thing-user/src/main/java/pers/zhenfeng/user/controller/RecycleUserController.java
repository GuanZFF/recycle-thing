package pers.zhenfeng.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.RecycleUserBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.NumberManage;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.user.mapper.RecycleUserMapper;
import pers.zhenfeng.user.po.RecycleUserPO;
import pers.zhenfeng.user.service.CommonService;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2019/01/16
 */
@RestController
@RequestMapping("/user")
public class RecycleUserController {

    @Resource
    private RecycleUserMapper recycleUserMapper;

    @Resource
    private CommonService commonService;

    @RequestMapping("/getUserByNo")
    public BaseResult<RecycleUserBO> getUserByNo(@RequestParam("userNo") String userNo) {
        if (StringUtils.isEmpty(userNo)) {
            return BaseResultUtil.failParam();
        }

        RecycleUserPO userPO = recycleUserMapper.getUserByNo(userNo);

        if (userPO == null) {
            return BaseResultUtil.fail("用户不存在");
        }

        RecycleUserBO userBO = new RecycleUserBO();

        BeanUtils.copyProperties(userPO, userBO);

        return BaseResultUtil.success(userBO);
    }

    @RequestMapping("/insert")
    public BaseResult<RecycleUserBO> insert(@RequestBody RecycleUserBO recycleUserBO) {
        RecycleUserPO recycleUserPO = new RecycleUserPO();
        BeanUtils.copyProperties(recycleUserBO, recycleUserPO);

        // 生成用户编号
        String userNo = NumberUtil.generateCollectorNo(commonService.getNumber(NumberManage.USER.getKey()));

        recycleUserPO.setUserNo(userNo);

        // 插入用户信息
        recycleUserMapper.insert(recycleUserPO);
        recycleUserBO.setId(recycleUserPO.getId());
        recycleUserBO.setUserNo(userNo);

        return BaseResultUtil.success(recycleUserBO);
    }

}
