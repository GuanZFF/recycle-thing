package pers.zhenfeng.service.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.QueryReverseOrderParam;
import pers.zhenfeng.api.bo.RecycleReverseOrderBO;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BasePageUtil;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.service.constant.NumberManage;
import pers.zhenfeng.service.mapper.RecycleReverseOrderMapper;
import pers.zhenfeng.service.po.RecycleReverseOrderPO;
import pers.zhenfeng.service.service.CommonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/12/29
 */
@RestController
@RequestMapping("reverse")
public class RecycleReverseOrderController {

    @Resource
    private CommonService commonService;

    @Resource
    private RecycleReverseOrderMapper recycleReverseOrderMapper;

    /**
     * 插入反向订单
     *
     * @param recycleReverseOrderBO 插入数据
     *
     * @return 插入结果
     */
    @RequestMapping("insert")
    public BaseResult<Integer> insert(@RequestBody RecycleReverseOrderBO recycleReverseOrderBO) {
        RecycleReverseOrderPO reverseOrderPO = new RecycleReverseOrderPO();
        BeanUtils.copyProperties(recycleReverseOrderBO, reverseOrderPO);

        // 生成商品编号
        String orderNo = NumberUtil.generateOrderNo(commonService.getNumber(NumberManage.REVERSE_ORDER.getKey()));

        reverseOrderPO.setOrderNo(orderNo);

        Integer insertResult = recycleReverseOrderMapper.insert(reverseOrderPO);

        return BaseResultUtil.success(insertResult);
    }

    /**
     * 通过用户ID获取用户订单
     *
     * @param uid 用户ID
     *
     * @return 用户订单列表
     */
    @RequestMapping("getReverseOrders")
    public BaseResult<List<RecycleReverseOrderBO>> getReverseOrders(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return BaseResultUtil.failParam();
        }

        // 获取数据库中数据
        List<RecycleReverseOrderPO> reverseOrders = recycleReverseOrderMapper.getReverseOrders(uid);
        if (CollectionUtils.isEmpty(reverseOrders)) {
            return BaseResultUtil.emptyList();
        }

        List<RecycleReverseOrderBO> reverseOrderBOS = Lists.newArrayList();

        // copy数据
        reverseOrders.forEach(item -> {
            RecycleReverseOrderBO recycleReverseOrderBO = new RecycleReverseOrderBO();
            BeanUtils.copyProperties(item, recycleReverseOrderBO);
            reverseOrderBOS.add(recycleReverseOrderBO);
        });

        return BaseResultUtil.success(reverseOrderBOS);
    }

    /**
     * 分页获取反向订单列表
     *
     * @param param 请求参数
     *
     * @return 分页结果
     */
    @RequestMapping(value = "getReverseOrderPage", method = RequestMethod.POST)
    public BaseResult<BasePage<RecycleReverseOrderBO>> getReverseOrderPage(@RequestBody QueryReverseOrderParam param) {
        Integer index = (param.getPageNum() - 1) * param.getPageSize();

        // 获取总数量
        Integer count = recycleReverseOrderMapper.getReverseOrderPageCount(param);
        if (count == null || count == 0) {
            return BaseResultUtil.success(BasePageUtil.emptyPage());
        }

        // 获取分页数据
        List<RecycleReverseOrderPO> list = recycleReverseOrderMapper.getReverseOrderPage(index, param.getPageSize(), param);

        List<RecycleReverseOrderBO> recycleOrderBOS = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return BaseResultUtil.success(BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleOrderBOS));
        }

        // 封装数据，数据转化PO -> BO
        BeanUtils.copyProperties(list, recycleOrderBOS);
        list.forEach(item -> {
            RecycleReverseOrderBO recycleReverseOrderBO = new RecycleReverseOrderBO();
            BeanUtils.copyProperties(item, recycleReverseOrderBO);
            recycleOrderBOS.add(recycleReverseOrderBO);
        });

        // 封装分页信息
        BasePage<RecycleReverseOrderBO> basePage = BasePageUtil.successPage(param.getPageNum(), param.getPageSize(), count, recycleOrderBOS);

        return BaseResultUtil.success(basePage);
    }
}
