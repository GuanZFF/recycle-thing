package pers.zhenfeng.api.bo;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
public class QueryCommodityParam extends QueryPage{
    private String commodityNo;

    private Integer commodityStatus;

    public String getCommodityNo() {
        return commodityNo;
    }

    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }
}
