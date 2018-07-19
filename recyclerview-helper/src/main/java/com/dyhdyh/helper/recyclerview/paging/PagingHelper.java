package com.dyhdyh.helper.recyclerview.paging;

/**
 * 以页码形式的分页
 *
 * @author dengyuhan
 *         created 2018/7/4 14:49
 */
public abstract class PagingHelper implements Pagination<Integer> {
    private int mPage;

    //起始页码
    private int mStartPage;

    //每页数据数量
    private int mPageCount;

    public PagingHelper(int startPage, int pageCount) {
        this.mStartPage = startPage;
        this.mPageCount = pageCount;
        this.resetPage();
    }

    @Override
    public boolean isFirst() {
        return mPage == mStartPage;
    }

    @Override
    public Integer getPage() {
        return mPage;
    }

    @Override
    public void nextPage() {
        mPage++;
    }

    @Override
    public int getPageCount() {
        return mPageCount;
    }

    @Override
    public void resetPage() {
        mPage = mStartPage;
    }

    @Override
    public Integer getStartPage() {
        return mStartPage;
    }
}
