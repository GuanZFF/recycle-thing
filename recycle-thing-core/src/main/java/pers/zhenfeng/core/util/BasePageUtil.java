package pers.zhenfeng.core.util;

import pers.zhenfeng.core.base.BasePage;

import java.util.Collections;
import java.util.List;

public class BasePageUtil {

    private static Integer defaultPageSize = 10;

    public static <T> BasePage<T> successPage(Integer pageNum, Integer pageSize, Integer count, List<T> data) {
        BasePage<T> basePage = new BasePage<>();
        basePage.setPageNum(pageNum);
        basePage.setPageSize(pageSize);
        basePage.setCount(count);
        basePage.setList(data);
        basePage.setHasNextPage(pageNum * pageSize < count);
        return basePage;
    }

    public static <T> BasePage<T> emptyPage() {
        BasePage<T> basePage = new BasePage<>();
        basePage.setPageNum(0);
        basePage.setPageSize(defaultPageSize);
        basePage.setCount(0);
        basePage.setHasNextPage(false);
        basePage.setList(Collections.<T>emptyList());
        return basePage;
    }

}
