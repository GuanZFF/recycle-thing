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
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.DateUtil;
import pers.zhenfeng.web.vo.RecycleOrderVO;

import javax.annotation.Resource;
import java.util.Collections;
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
    public BaseResult<Integer> insert(@RequestBody RecycleOrderVO recycleOrderVO) {
        RecycleOrderBO recycleOrderBO = new RecycleOrderBO();

        BeanUtils.copyProperties(recycleOrderVO, recycleOrderBO);

        return recycleOrderService.insert(recycleOrderBO);
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
            BasePage<RecycleOrderVO> basePage = new BasePage<>();

            return BaseResultUtil.success(basePage);
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
