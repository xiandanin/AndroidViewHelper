package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author dengyuhan
 *         created 2018/7/4 14:34
 */
public class LoadMoreHelper {
    protected RecyclerView mRecyclerView;

    protected LoadMoreView mLoadMoreView;
    private OnLoadMoreScrollListener mScrollListener;

    private LoadMoreAdapter mLoadMoreAdapter;


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
        mScrollListener = new OnLoadMoreScrollListener();
        mScrollListener.setEarlyCountForAutoLoad(earlyCountForAutoLoad);
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    public void setOnFindVisibleItemCallback(OnFindVisibleItemCallback callback) {
        mScrollListener.setOnFindVisibleItemCallback(callback);
    }

    public void setEarlyCountForAutoLoad(int earlyCountForAutoLoad) {
        mScrollListener.setEarlyCountForAutoLoad(earlyCountForAutoLoad);
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
        mLoadMoreAdapter = new LoadMoreAdapter(adapter, mLoadMoreView);
        mRecyclerView.setAdapter(mLoadMoreAdapter);
    }


    public void setLoadMoreState(int state) {
        if (mLoadMoreAdapter != null) {
            mLoadMoreAdapter.setLoadMoreState(state);
        }
    }

    public int getLoadMoreState() {
        if (mLoadMoreAdapter != null) {
            return mLoadMoreAdapter.getLoadMoreState();
        }
        return LoadMoreView.GONE;
    }


    public void setLoadMoreEnable(boolean enable) {
        this.mScrollListener.setLoadMoreEnable(enable);
    }

    public OnLoadMoreScrollListener getScrollListener() {
        return mScrollListener;
    }
}
