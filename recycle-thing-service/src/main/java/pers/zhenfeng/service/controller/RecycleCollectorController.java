package pers.zhenfeng.service.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.api.bo.RecycleCollectorBO;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.CommodityStatus;
import pers.zhenfeng.core.constant.RedisKeyPrefix;
import pers.zhenfeng.core.constant.ResultMsg;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.core.util.RedisUtil;
import pers.zhenfeng.core.constant.NumberManage;
import pers.zhenfeng.service.mapper.RecycleCollectorMapper;
import pers.zhenfeng.service.po.RecycleCollectorPO;
import pers.zhenfeng.service.service.CommonService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("collector")
public class RecycleCollectorController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RecycleCollectorController.class);

    @Resource
    private RecycleCollectorMapper recycleCollectorMapper;

    @Resource
    private CommonService commonService;

    @RequestMapping("getRecycleCollector/{id}")
    public BaseResult<RecycleCollectorBO> getRecycleCollector(@PathVariable("id") Integer id) {
        RecycleCollectorPO recycleCommodityPO = recycleCollectorMapper.getRecycleCollector(id);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.success();
        }

        RecycleCollectorBO recycleCollectorBO = new RecycleCollectorBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCollectorBO);

        return BaseResultUtil.success(recycleCollectorBO);
    }

    /**
     * 通过收货人编号获取收货人信息（redis中存在时优先调用redis）
     *
     * @param collectorNo 收货人单号
     *
     * @return 收货人信息
     */
    @RequestMapping("getRecycleCollectorByNo")
    public BaseResult<RecycleCollectorBO> getRecycleCollectorByNo(@RequestParam("collectorNo") String collectorNo) {

        try {
            String collector = RedisUtil.getValueByKey(RedisKeyPrefix.recycleCollector + collectorNo);
            LOGGER.info(collector);

            if (!StringUtils.isEmpty(collector)) {
                RecycleCollectorBO recycleCollectorBO = JSON.parseObject(collector, RecycleCollectorBO.class);
                return BaseResultUtil.success(recycleCollectorBO);
            }
        } catch (Exception e) {
            LOGGER.error("Get recycleCollectorBO failure by the redis", e);
        }


        RecycleCollectorPO recycleCommodityPO = recycleCollectorMapper.getRecycleCollectorByNo(collectorNo);
        if (ObjectUtils.isEmpty(recycleCommodityPO)) {
            return BaseResultUtil.fail(ResultMsg.QUERY_DATA_NULL.getMsg());
        }

        RecycleCollectorBO recycleCollectorBO = new RecycleCollectorBO();

        BeanUtils.copyProperties(recycleCommodityPO, recycleCollectorBO);

        try {
            RedisUtil.setValue(RedisKeyPrefix.recycleCollector + collectorNo, JSON.toJSONString(recycleCollectorBO));
        } catch (Exception e) {
            LOGGER.error("Set recycleCollectorBO failure by the redis", e);
        }

        return BaseResultUtil.success(recycleCollectorBO);
    }

    @RequestMapping("getAllRecycleCollector")
    public BaseResult<List<RecycleCollectorBO>> getAllRecycleCollector() {
        List<RecycleCollectorPO> recycleCollectorPOS = recycleCollectorMapper.getAllRecycleCollector();
        if (CollectionUtils.isEmpty(recycleCollectorPOS)) {
            return BaseResultUtil.success();
        }

        List<RecycleCollectorBO> recycleCollectorBOS = recycleCollectorPOS.stream().map(recycleCollectorPO -> {
            RecycleCollectorBO recycleCollectorBO = new RecycleCollectorBO();
            BeanUtils.copyProperties(recycleCollectorPO, recycleCollectorBO);
            return recycleCollectorBO;
        }).collect(Collectors.toList());

        return BaseResultUtil.success(recycleCollectorBOS);
    }

    @RequestMapping("insertRecycleCollector")
    public BaseResult<Integer> insertRecycleCollector(@RequestBody RecycleCollectorBO recycleCollectorBO) {
        RecycleCollectorPO recycleCollectorPO = new RecycleCollectorPO();
        BeanUtils.copyProperties(recycleCollectorBO, recycleCollectorPO);

        // 生成商品编号
        String commodityNo = NumberUtil.generateCollectorNo(commonService.getNumber(NumberManage.COLLECTOR.getKey()));

        recycleCollectorPO.setCollectorNo(commodityNo);
        recycleCollectorPO.setStatus(CommodityStatus.INIT.getCode());

        Integer id = recycleCollectorMapper.insertRecycleCollector(recycleCollectorPO);

        return BaseResultUtil.success(id);
    }

    /**
     * 根据收集人单号删除收集人
     *
     * @param collectorNo 收集人单号
     *
     * @return 删除结果
     */
    @RequestMapping("deleteRecycleCollector")
    public BaseResult<Integer> deleteRecycleCollector(@RequestParam("collectorNo") String collectorNo) {
        try {
            RedisUtil.remove(RedisKeyPrefix.recycleCollector + collectorNo);
        } catch (Exception e) {
            LOGGER.error("Del recycleCollectorBO failure by the redis", e);
        }

        if (StringUtils.isEmpty(collectorNo)) {
            return BaseResultUtil.fail(ResultMsg.PARAM_ERROR.getMsg());
        }

        Integer result = recycleCollectorMapper.deleteRecycleCollector(collectorNo);

        return BaseResultUtil.success(result);
    }
}
