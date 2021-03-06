package pers.zhenfeng.web.vo;

import pers.zhenfeng.api.bo.RecycleCollectorBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/25
 */
public class RecycleCommodityVO {
    private Integer id;

    private String collectorNo;

    private Integer villageId;

    private String commodityNo;

    private String commodityName;

    private String commodityPicture;

    private List<String> imgUrl;

    private Integer commodityStatus;

    private Integer commodityType;

    private String commodityTypeDesc;

    private String recycleTime;

    private BigDecimal recyclePrice;

    private BigDecimal expectSellPrice;

    private BigDecimal actualSellPrice;

    private String remark;

    private RecycleCollectorBO recycleCollectorBO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollectorNo() {
        return collectorNo;
    }

    public void setCollectorNo(String collectorNo) {
        this.collectorNo = collectorNo;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public String getCommodityNo() {
        return commodityNo;
    }

    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String commodityPicture) {
        this.commodityPicture = commodityPicture;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public String getRecycleTime() {
        return recycleTime;
    }

    public void setRecycleTime(String recycleTime) {
        this.recycleTime = recycleTime;
    }

    public BigDecimal getRecyclePrice() {
        return recyclePrice;
    }

    public void setRecyclePrice(BigDecimal recyclePrice) {
        this.recyclePrice = recyclePrice;
    }

    public BigDecimal getExpectSellPrice() {
        return expectSellPrice;
    }

    public void setExpectSellPrice(BigDecimal expectSellPrice) {
        this.expectSellPrice = expectSellPrice;
    }

    public BigDecimal getActualSellPrice() {
        return actualSellPrice;
    }

    public void setActualSellPrice(BigDecimal actualSellPrice) {
        this.actualSellPrice = actualSellPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCommodityTypeDesc() {
        return commodityTypeDesc;
    }

    public void setCommodityTypeDesc(String commodityTypeDesc) {
        this.commodityTypeDesc = commodityTypeDesc;
    }

    public RecycleCollectorBO getRecycleCollectorBO() {
        return recycleCollectorBO;
    }

    public void setRecycleCollectorBO(RecycleCollectorBO recycleCollectorBO) {
        this.recycleCollectorBO = recycleCollectorBO;
    }
}
