package com.dyhdyh.helper.recyclerview.loadmore;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.dyhdyh.helper.recyclerview.R;
import com.dyhdyh.helper.recyclerview.loadmore.valyout.VLayoutLoadMoreHelper;
import com.dyhdyh.helper.recyclerview.paging.Pagination;
import com.dyhdyh.helper.recyclerview.paging.PagingHelper;
import com.dyhdyh.helper.recyclerview.subscribers.PagingViewController;

/**
 * 刷新和更多的RecyclerView
 *
 * @author dengyuhan
 *         created 2018/7/18 15:19
 */
public class SimpleRecyclerView extends RelativeLayout implements Pagination<Integer>, PagingViewController {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LoadMoreHelper mLoadMoreHelper;
    private Pagination<Integer> mPagingHelper;

    private boolean mEnableLoadMore;
    private boolean mEnableRefresh;
    private boolean mVLayoutSupport;

    private OnRefreshListener2 mOnRefreshListener;

    private boolean mIsUseInitLoading = true;

    public SimpleRecyclerView(Context context) {
        this(context, null);
    }

    public SimpleRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);

        mRecyclerView = new RecyclerView(context);
        if (mEnableRefresh) {
            mRefreshLayout = new SwipeRefreshLayout(context);
            mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onRefresh(true);
                    }
                }
            });

            RelativeLayout parentLayout = new RelativeLayout(context);
            parentLayout.addView(mRecyclerView);
            mRefreshLayout.addView(parentLayout);
            this.addView(mRefreshLayout);
        } else {
            this.addView(mRecyclerView);
        }
        if (mEnableLoadMore) {

            mLoadMoreHelper = mVLayoutSupport ? new VLayoutLoadMoreHelper(mRecyclerView) : new LoadMoreHelper(mRecyclerView);

            mLoadMoreHelper.setLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onRefresh(false);
                    }
                }
            });

            mPagingHelper = new SimplePagingHelper();
        }
    }

    protected void initAttrs(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleRecyclerView);
        mEnableRefresh = a.getBoolean(R.styleable.SimpleRecyclerView_enableRefresh, true);
        mEnableLoadMore = a.getBoolean(R.styleable.SimpleRecyclerView_enableLoadMore, true);
        mVLayoutSupport = a.getBoolean(R.styleable.SimpleRecyclerView_vlayoutSupport, false);
        a.recycle();
    }

    public View getRecyclerViewParent() {
        return (View) mRecyclerView.getParent();
    }

    public void setPagingHelper(Pagination<Integer> pagingHelper) {
        this.mPagingHelper = pagingHelper;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        mRecyclerView.setLayoutManager(layout);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mEnableLoadMore && mLoadMoreHelper != null) {
            mLoadMoreHelper.setAdapter(adapter);
        } else {
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean isFirst() {
        return mPagingHelper.isFirst();
    }

    @Override
    public Integer getPage() {
        return mPagingHelper.getPage();
    }

    @Override
    public void nextPage() {
        mPagingHelper.nextPage();
    }

    @Override
    public Integer getStartPage() {
        return mPagingHelper.getStartPage();
    }

    @Override
    public int getPageCount() {
        return mPagingHelper.getPageCount();
    }

    @Override
    public void resetPage() {
        mPagingHelper.resetPage();
    }

    public RecyclerView getView() {
        return mRecyclerView;
    }

    public SwipeRefreshLayout getRefreshView() {
        return mRefreshLayout;
    }

    public void setOnRefreshListener(OnRefreshListener2 listener) {
        this.mOnRefreshListener = listener;
    }

    @Override
    public View getEmptyParentLayout() {
        return (View) mRecyclerView.getParent();
    }

    @Override
    public boolean isUseInitLoading() {
        return mIsUseInitLoading;
    }

    @Override
    public void setUseInitLoading(boolean useInitLoading) {
        mIsUseInitLoading = useInitLoading;
    }

    @Override
    public void setCompleted() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
        if (mLoadMoreHelper != null) {
            mLoadMoreHelper.setLoadMoreCompleted();
        }
    }

    @Override
    public void setLoadMoreError(CharSequence errorMessage) {
        setLoadMoreState(LoadMoreView.ERROR);
    }

    @Override
    public void setLoadMore() {
        setLoadMoreState(LoadMoreView.LOADING);
    }

    @Override
    public void setTheEnd() {
        setLoadMoreState(LoadMoreView.THE_END);
    }

    public void setLoadMoreState(int state) {
        if (mLoadMoreHelper != null) {
            mLoadMoreHelper.setLoadMoreState(state);
            if (LoadMoreView.THE_END == state) {
                mLoadMoreHelper.setLoadMoreEnable(false);
            }
        }
    }

    public int getLoadMoreState() {
        if (mLoadMoreHelper != null) {
            return mLoadMoreHelper.getLoadMoreState();
        }
        return LoadMoreView.GONE;
    }

    /**
     * 是否开启了加载更多
     *
     * @return
     */
    public boolean isLoadMoreEnable() {
        return mEnableLoadMore;
    }

    public interface OnRefreshListener2 {

        void onRefresh(boolean refresh);
    }


    public final static class SimplePagingHelper extends PagingHelper {

        public SimplePagingHelper() {
            super(1, 20);
        }
    }


}
