package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author dengyuhan
 *         created 2018/7/4 16:04
 */
public class LoadMoreDelegateAdapter {
    private int mState = LoadMoreView.LOADING;
    private LoadMoreView mLoadMoreView;

    public LoadMoreDelegateAdapter(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mLoadMoreView == null) {
            mLoadMoreView = new LoadingFooterView(parent.getContext());
        }
        return new RecyclerView.ViewHolder(mLoadMoreView.getView()) {
        };
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LoadMoreView) {
            LoadMoreView loadMoreView = (LoadMoreView) holder.itemView;
            loadMoreView.setLoadMoreState(mState);
        }
    }

    public void setLoadMoreState(int state) {
        this.mState = state;
        if (mLoadMoreView != null) {
            this.mLoadMoreView.setLoadMoreState(mState);
        }
    }

    public int getLoadMoreState() {
        return mState;
    }

    public interface Delegate {

        void setLoadMoreState(int state);

        int getLoadMoreState();
    }

}
