package pers.zhenfeng.web.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.QueryOrderParam;
import pers.zhenfeng.api.bo.RecycleOrderBO;
import pers.zhenfeng.api.service.RecycleOrderService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.OrderStatus;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.DateUtil;
import pers.zhenfeng.web.vo.RecycleOrderVO;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/29
 */
@RestController
@RequestMapping("/order")
public class RecycleOrderController {

    @Resource
    private RecycleOrderService recycleOrderService;

    /**
     * 插入订单数据
     *
     * @param recycleOrderVO 订单信息
     *
     * @return 插入结果
     */
    @RequestMapping("insert")
    public BaseResult<RecycleOrderVO> insert(@RequestBody RecycleOrderVO recycleOrderVO) {
        RecycleOrderBO recycleOrderBO = new RecycleOrderBO();

        BeanUtils.copyProperties(recycleOrderVO, recycleOrderBO);

        if (StringUtils.isEmpty(recycleOrderVO.getStartTime()) || StringUtils.isEmpty(recycleOrderVO.getEndTime())) {
            Calendar calendar = Calendar.getInstance();
            recycleOrderBO.setStartTime(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
            recycleOrderBO.setEndTime(calendar.getTime());
        } else {
            recycleOrderBO.setStartTime(DateUtil.getStringToDate(recycleOrderVO.getStartTime()));
            recycleOrderBO.setEndTime(DateUtil.getStringToDate(recycleOrderVO.getEndTime()));
        }

        BaseResult<RecycleOrderBO> insertResult = recycleOrderService.insert(recycleOrderBO);
        if (BaseResultUtil.isFail(insertResult)) {
            return BaseResultUtil.fail(insertResult.getMsg());
        }
        RecycleOrderBO insertOrderBO = insertResult.getData();
        recycleOrderVO.setId(insertOrderBO.getId());
        recycleOrderVO.setOrderNo(insertOrderBO.getOrderNo());

        return BaseResultUtil.success(recycleOrderVO);
    }

    /**
     * 通过订单号获取订单详情
     *
     * @param orderNo 订单号码
     *
     * @return 订单详情
     */
    @RequestMapping("getRecycleOrder")
    public BaseResult<RecycleOrderVO> getRecycleOrder(@RequestParam("orderNo") String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return BaseResultUtil.failParam();
        }

        BaseResult<RecycleOrderBO> recycleOrder = recycleOrderService.getRecycleOrder(orderNo);
        if (BaseResultUtil.isFail(recycleOrder)) {
            return BaseResultUtil.fail(recycleOrder.getCode(), recycleOrder.getMsg());
        }

        RecycleOrderBO recycleOrderBO = recycleOrder.getData();

        RecycleOrderVO recycleOrderVO = new RecycleOrderVO();
        recycleOrderVO.setOrderTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD_HH_MM_SS, recycleOrderBO.getStartTime()));
        BeanUtils.copyProperties(recycleOrderBO, recycleOrderVO);

        return BaseResultUtil.success(recycleOrderVO);
    }

    /**
     * 通过用户ID获取订单列表
     *
     * @param uid 用户唯一ID
     *
     * @return 订单列表
     */
    @RequestMapping("getRecycleOrderList")
    public BaseResult<List<RecycleOrderVO>> getRecycleOrderList(@RequestParam("uid") String uid) {
        if (StringUtils.isEmpty(uid)) {
            return BaseResultUtil.emptyList();
        }

        // 读取服务订单数据
        BaseResult<List<RecycleOrderBO>> result = recycleOrderService.getRecycleOrderList(uid);
        if (BaseResultUtil.isFail(result)) {
            return BaseResultUtil.fail(result.getMsg());
        }

        // 判断订单是否为空
        List<RecycleOrderBO> recycleOrderBOS = result.getData();
        if (CollectionUtils.isEmpty(recycleOrderBOS)) {
            return BaseResultUtil.success(Collections.emptyList());
        }

        List<RecycleOrderVO> recycleOrderVOS = Lists.newArrayList();

        // COPY订单数据
        recycleOrderBOS.forEach(recycleOrderBO -> {
            RecycleOrderVO recycleOrderVO = new RecycleOrderVO();
            recycleOrderVOS.add(recycleOrderVO);

            BeanUtils.copyProperties(recycleOrderBO, recycleOrderVO);
        });

        return BaseResultUtil.success(recycleOrderVOS);
    }

    /**
     * 分页获取用户订单列表
     *
     * @param pageNum  页号
     * @param pageSize 页的数量
     * @param uid      微信唯一值
     *
     * @return 分页订单数据
     */
    @RequestMapping("getRecycleOrderPage")
    public BaseResult<BasePage<RecycleOrderVO>> getRecycleOrderPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam("uid") String uid) {
        if (StringUtils.isEmpty(uid)) {
            BaseResultUtil.fail("没有Uid参数");
        }

        // 封装参数
        QueryOrderParam queryOrderParam = new QueryOrderParam();
        queryOrderParam.setUid(uid);
        queryOrderParam.setPageNum(pageNum);
        queryOrderParam.setPageSize(pageSize);

        // 获取服务器数据
        BaseResult<BasePage<RecycleOrderBO>> recycleOrderPage = recycleOrderService.getRecycleOrderPage(queryOrderParam);
        if (BaseResultUtil.isFail(recycleOrderPage)) {
            return BaseResultUtil.fail(recycleOrderPage.getMsg());
        }
        BasePage<RecycleOrderBO> orderPageData = recycleOrderPage.getData();

        // 封装返回的信息
        BasePage<RecycleOrderVO> basePage = new BasePage<>();
        basePage.setHasNextPage(orderPageData.getHasNextPage());
        basePage.setCount(orderPageData.getCount());
        basePage.setPageNum(orderPageData.getPageNum());
        basePage.setPageSize(orderPageData.getPageSize());

        List<RecycleOrderVO> orderVOS = Lists.newArrayList();
        basePage.setList(orderVOS);

        // 转换BO到VO
        orderPageData.getList().forEach(item -> {
            RecycleOrderVO recycleOrderVO = new RecycleOrderVO();
            BeanUtils.copyProperties(item, recycleOrderVO);

            recycleOrderVO.setOrderTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD_HH_MM_SS, item.getOrderTime()));

            orderVOS.add(recycleOrderVO);
        });

        return BaseResultUtil.success(basePage);
    }

    public static <T, E> BasePage<T> convertPageObj(BasePage<E> source) {

        return null;
    }

}
