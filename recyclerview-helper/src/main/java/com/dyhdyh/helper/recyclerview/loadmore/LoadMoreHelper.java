package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.dyhdyh.helper.recyclerview.loadmore.valyout.LoadMoreVLayoutAdapter;

/**
 * @author dengyuhan
 *         created 2018/7/4 14:34
 */
public class LoadMoreHelper {
    private RecyclerView mRecyclerView;

    private LoadMoreView mLoadMoreView;
    private LoadMoreDelegateAdapter.Delegate mDelegateAdapter;
    private OnLoadMoreScrollListener mScrollListener;

    public LoadMoreHelper(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        //自动计算earlyCountForAutoLoad
        final RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
        int earlyCountForAutoLoad;
        if (lm instanceof StaggeredGridLayoutManager) {
            earlyCountForAutoLoad = ((StaggeredGridLayoutManager) lm).getSpanCount();
        } else {
            earlyCountForAutoLoad = OnLoadMoreScrollListener.DEFAULT_AUTOLOAD_COUNT;
        }

        mScrollListener = new OnLoadMoreScrollListener(earlyCountForAutoLoad);
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public void setLoadMoreListener(OnLoadMoreListener listener) {
        mScrollListener.setLoadMoreListener(listener);
    }

    public void setLoadMoreCompleted() {
        mScrollListener.setLoadMoreCompleted();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LoadMoreAdapter loadMoreAdapter = new LoadMoreAdapter(adapter, mLoadMoreView);
        mDelegateAdapter = loadMoreAdapter;

        mRecyclerView.setAdapter(loadMoreAdapter);
    }

    public void setAdapter(DelegateAdapter adapter) {
        LoadMoreVLayoutAdapter loadMoreAdapter = new LoadMoreVLayoutAdapter(mLoadMoreView);
        mDelegateAdapter = loadMoreAdapter;

        adapter.addAdapter(loadMoreAdapter);
        mRecyclerView.setAdapter(adapter);
    }

    public void setLoadMoreState(int state) {
        if (mDelegateAdapter != null) {
            mDelegateAdapter.setLoadMoreState(state);
        }
    }

    public int getLoadMoreState() {
        if (mDelegateAdapter != null) {
            return mDelegateAdapter.getLoadMoreState();
        }
        return LoadMoreView.GONE;
    }

    public OnLoadMoreScrollListener getScrollListener() {
        return mScrollListener;
    }
}
