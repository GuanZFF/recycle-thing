package pers.zhenfeng.web.controller;


import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.api.bo.QueryReverseOrderParam;
import pers.zhenfeng.api.bo.RecycleReverseOrderBO;
import pers.zhenfeng.api.service.RecycleReverseOrderService;
import pers.zhenfeng.core.base.BasePage;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.DateUtil;
import pers.zhenfeng.web.vo.RecycleReverseOrderVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2019/01/03
 */
@RestController
@RequestMapping("/reverse")
public class RecycleReverseOrderController {

    @Resource
    private RecycleReverseOrderService recycleReverseOrderService;

    /**
     * 插入反向订单数据
     *
     * @param recycleReverseOrderVO 反向订单信息
     *
     * @return 插入结果
     */
    @RequestMapping("insert")
    public BaseResult<Integer> insert(@RequestBody RecycleReverseOrderVO recycleReverseOrderVO) {
        RecycleReverseOrderBO reverseOrderBO = new RecycleReverseOrderBO();

        BeanUtils.copyProperties(recycleReverseOrderVO, reverseOrderBO);

        return recycleReverseOrderService.insert(reverseOrderBO);
    }

    /**
     * 分页获取用户反向订单列表
     *
     * @param pageNum  页号
     * @param pageSize 页的数量
     * @param uid      微信唯一值
     *
     * @return 分页订单数据
     */
    @RequestMapping("getReverseOrderPage")
    public BaseResult<BasePage<RecycleReverseOrderVO>> getRecycleOrderPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                           @RequestParam("uid") String uid) {
        if (StringUtils.isEmpty(uid)) {
            BaseResultUtil.fail("没有Uid参数");
        }

        // 封装参数
        QueryReverseOrderParam reverseOrderParam = new QueryReverseOrderParam();
        reverseOrderParam.setUid(uid);
        reverseOrderParam.setPageNum(pageNum);
        reverseOrderParam.setPageSize(pageSize);

        // 获取服务器数据
        BaseResult<BasePage<RecycleReverseOrderBO>> recycleOrderPage = recycleReverseOrderService.getReverseOrderPage(reverseOrderParam);
        if (BaseResultUtil.isFail(recycleOrderPage)) {
            BasePage<RecycleReverseOrderVO> basePage = new BasePage<>();

            return BaseResultUtil.success(basePage);
        }
        BasePage<RecycleReverseOrderBO> orderPageData = recycleOrderPage.getData();

        // 封装返回的信息
        BasePage<RecycleReverseOrderVO> basePage = new BasePage<>();
        basePage.setHasNextPage(orderPageData.getHasNextPage());
        basePage.setCount(orderPageData.getCount());
        basePage.setPageNum(orderPageData.getPageNum());
        basePage.setPageSize(orderPageData.getPageSize());

        List<RecycleReverseOrderVO> orderVOS = Lists.newArrayList();
        basePage.setList(orderVOS);

        // 转换BO到VO
        orderPageData.getList().forEach(item -> {
            RecycleReverseOrderVO reverseOrderVO = new RecycleReverseOrderVO();
            BeanUtils.copyProperties(item, reverseOrderVO);

            reverseOrderVO.setStartTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD_HH_MM_SS, item.getStartTime()));
            reverseOrderVO.setEndTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD_HH_MM_SS, item.getEndTime()));

            orderVOS.add(reverseOrderVO);
        });

        return BaseResultUtil.success(basePage);
    }
}
