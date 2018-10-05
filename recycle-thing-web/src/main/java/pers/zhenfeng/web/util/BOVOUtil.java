package pers.zhenfeng.web.util;

import org.springframework.util.StringUtils;
import pers.zhenfeng.api.bo.RecycleCommodityBO;
import pers.zhenfeng.core.util.DateUtil;
import pers.zhenfeng.web.vo.RecycleCommodityVO;

import java.util.Arrays;

/**
 * @author Grow-Worm
 * @date 2018/09/25
 */
public class BOVOUtil {

    public static RecycleCommodityVO buildRecycleCommodityVO(RecycleCommodityBO recycleCommodityBO) {
        RecycleCommodityVO recycleCommodityVO = new RecycleCommodityVO();
        recycleCommodityVO.setId(recycleCommodityBO.getId());
        recycleCommodityVO.setActualSellPrice(recycleCommodityBO.getActualSellPrice());
        recycleCommodityVO.setCollectorNo(recycleCommodityBO.getCollectorNo());
        recycleCommodityVO.setCommodityName(recycleCommodityBO.getCommodityName());
        recycleCommodityVO.setCommodityNo(recycleCommodityBO.getCommodityNo());
        recycleCommodityVO.setCommodityPicture(recycleCommodityBO.getCommodityPicture());
        recycleCommodityVO.setCommodityStatus(recycleCommodityBO.getCommodityStatus());
        recycleCommodityVO.setCommodityType(recycleCommodityBO.getCommodityType());
        recycleCommodityVO.setExpectSellPrice(recycleCommodityBO.getExpectSellPrice());
        recycleCommodityVO.setVillageId(recycleCommodityBO.getVillageId());
        recycleCommodityVO.setRemark(recycleCommodityBO.getRemark());
        recycleCommodityVO.setRecyclePrice(recycleCommodityBO.getRecyclePrice());

        recycleCommodityVO.setRecycleTime(DateUtil.getDateString(DateUtil.YYYY_MM_DD_HH_MM_SS, recycleCommodityBO.getRecycleTime()));
        String imgUrl = recycleCommodityBO.getImgUrl();
        recycleCommodityVO.setImgUrl(StringUtils.isEmpty(imgUrl) ? null : Arrays.asList(imgUrl.split(",")));

        return recycleCommodityVO;
    }

}
