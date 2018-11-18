package pers.zhenfeng.service.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.SsoTokenBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.mapper.RecycleTokenMapper;
import pers.zhenfeng.service.po.SsoTokenPO;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/11/18
 */
@RestController
@RequestMapping("token")
public class RecycleTokenController {

    @Resource
    private RecycleTokenMapper recycleTokenMapper;

    @RequestMapping("getToken")
    public BaseResult<SsoTokenBO> getTokenBySeries(@RequestParam("series") String series) {
        if (StringUtils.isEmpty(series)) {
            return BaseResultUtil.failParam();
        }
        SsoTokenPO tokenPO = recycleTokenMapper.getTokenBySeries(series);
        if (ObjectUtils.isEmpty(tokenPO)) {
            return BaseResultUtil.success();
        }

        SsoTokenBO tokenBO = new SsoTokenBO();

        BeanUtils.copyProperties(tokenPO, tokenBO);

        return BaseResultUtil.success(tokenBO);
    }

    @RequestMapping("insert")
    public BaseResult<Integer> insertToken(@RequestBody SsoTokenBO tokenBO) {
        if (ObjectUtils.isEmpty(tokenBO)) {
            return BaseResultUtil.failParam();
        }
        SsoTokenPO tokenPO = new SsoTokenPO();
        BeanUtils.copyProperties(tokenBO, tokenPO);

        return BaseResultUtil.success(recycleTokenMapper.insert(tokenPO));
    }

}
