package com.dyhdyh.helper.recyclerview.paging;

/**
 * 分页计算
 *
 * @author dengyuhan
 *         created 2018/7/4 14:43
 */
public interface Pagination<Page> {

    default boolean isFirst() {
        return getPage().equals(getStartPage());
    }

    Page getPage();

    void nextPage();

    Page getStartPage();

    int getPageCount();

    void resetPage();

}
