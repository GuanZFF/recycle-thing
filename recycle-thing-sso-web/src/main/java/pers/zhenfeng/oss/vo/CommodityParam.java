package pers.zhenfeng.oss.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/10/01
 */
public class CommodityParam {
    private Integer id;

    private String collectorNo;

    private Integer villageId;

    private String commodityNo;

    private String commodityName;

    private String commodityPicture;

    private List<String> imgUrl;

    private Integer commodityStatus;

    private Integer commodityType;

    private Date recycleTime;

    private BigDecimal recyclePrice;

    private BigDecimal expectSellPrice;

    private BigDecimal actualSellPrice;

    private Integer damageDegree;

    private String remark;

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

    public Date getRecycleTime() {
        return recycleTime;
    }

    public void setRecycleTime(Date recycleTime) {
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

    public Integer getDamageDegree() {
        return damageDegree;
    }

    public void setDamageDegree(Integer damageDegree) {
        this.damageDegree = damageDegree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
