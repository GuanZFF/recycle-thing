package pers.zhenfeng.api.bo;

import pers.zhenfeng.core.constant.Common;

/**
 * @author Grow-Worm
 * @date 2018/09/30
 */
public class QueryPage {
    private Integer pageNum = Common.DEFAULT_PAGE_NUM;

    private Integer pageSize = Common.DEFAULT_PAGE_SIZE;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
