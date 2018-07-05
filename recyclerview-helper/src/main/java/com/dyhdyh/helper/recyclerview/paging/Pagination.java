package com.dyhdyh.helper.recyclerview.paging;

/**
 * 分页计算
 *
 * @author dengyuhan
 *         created 2018/7/4 14:43
 */
public interface Pagination<Page> {
    Page getPage();

    void nextPage();

    int getPageCount();

    void resetPage();

    /**
     * 是否最后一页
     *
     * @return true表示最后一页
     */
    boolean isLast();
}
