package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;

import java.util.Arrays;

/**
 * @author dengyuhan
 *         created 2018/7/3 16:47
 */
public class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    public static final int DEFAULT_AUTOLOAD_COUNT = 1;

    private boolean mLoadMoreCompleted = true;

    private int mEarlyCountForAutoLoad;
    private OnLoadMoreListener mLoadMoreListener;

    public OnLoadMoreScrollListener() {
        this(DEFAULT_AUTOLOAD_COUNT);
    }

    public OnLoadMoreScrollListener(int earlyCountForAutoLoad) {
        this.mEarlyCountForAutoLoad = earlyCountForAutoLoad;
    }

    public void setLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (RecyclerView.SCROLL_STATE_IDLE == newState) {
            callScrollLoadMore(recyclerView);
        }
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //callScrollLoadMore(recyclerView);
    }

    private void callScrollLoadMore(RecyclerView recyclerView) {
        //hasMore: status of current page, means if there's more data, you have to maintain this status
        if (mLoadMoreListener != null && mLoadMoreCompleted) {
            int first = 0, last = 0, total = 0;
            final RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
            if (lm instanceof LinearLayoutManager) {
                //first = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
                last = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
            } else if (lm instanceof StaggeredGridLayoutManager) {
                final int[] firstPositions = ((StaggeredGridLayoutManager) lm).findFirstCompletelyVisibleItemPositions(null);
                Arrays.sort(firstPositions);
                //first = firstPositions[0];
                final int[] lastPositions = ((StaggeredGridLayoutManager) lm).findLastVisibleItemPositions(null);
                Arrays.sort(lastPositions);
                last = lastPositions[0];
            }
            total = getItemCount(recyclerView.getAdapter());
            if (!onIntercept(recyclerView, last, total)) {
                //earlyCountForAutoLoad: help to trigger load more listener earlier
                Log.d("-------------->", first + " " + last + " " + total);
                mLoadMoreCompleted = false;
                mLoadMoreListener.onLoadMore();
            }
        }
    }

    protected int getItemCount(RecyclerView.Adapter adapter) {
        if (adapter instanceof LoadMoreAdapter) {
            return ((LoadMoreAdapter) adapter).getInnerAdapter().getItemCount();
        } else if (adapter instanceof DelegateAdapter) {
            return adapter.getItemCount() - 1;
        } else {
            return adapter.getItemCount();
        }
    }

    protected boolean onIntercept(RecyclerView recyclerView, int last, int total) {
        return !(last > 0 && last >= total - mEarlyCountForAutoLoad);
    }

    public void setLoadMoreCompleted() {
        this.mLoadMoreCompleted = true;
    }
}
