package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/7/4 15:41
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LoadMoreDelegateAdapter.Delegate {

    private LoadMoreDelegateAdapter mDelegateAdapter;
    private RecyclerView.Adapter mInnerAdapter;

    private final int TYPE_LOADMORE_VIEW = Integer.MAX_VALUE;

    public LoadMoreAdapter(RecyclerView.Adapter adapter) {
        this(adapter, null);
    }

    public LoadMoreAdapter(RecyclerView.Adapter adapter, LoadMoreView loadMoreView) {
        this.mInnerAdapter = adapter;
        this.mDelegateAdapter = new LoadMoreDelegateAdapter(loadMoreView);
    }

    @Override
    public void setLoadMoreState(int state) {
        mDelegateAdapter.setLoadMoreState(state);
        notifyItemChanged(getLoadMorePosition());
    }

    @Override
    public int getLoadMoreState() {
        return mDelegateAdapter.getLoadMoreState();
    }

    public int getLoadMorePosition() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADMORE_VIEW) {
            return mDelegateAdapter.onCreateViewHolder(parent);
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (getItemViewType(position) == TYPE_LOADMORE_VIEW) {
            setLoadMoreViewParams(holder);
            mDelegateAdapter.onBindViewHolder(holder);
        } else {
            mInnerAdapter.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_LOADMORE_VIEW) {
            setLoadMoreViewParams(holder);
            mDelegateAdapter.onBindViewHolder(holder);
        } else {
            mInnerAdapter.onBindViewHolder(holder, position);
        }
    }

    private void setLoadMoreViewParams(RecyclerView.ViewHolder holder) {
        //加载更多View
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }


    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getLoadMorePosition()) {
            return TYPE_LOADMORE_VIEW;
        } else {
            return mInnerAdapter.getItemViewType(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return mInnerAdapter.getItemId(position);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mInnerAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        /*
        if (TYPE_LOADMORE_VIEW == getItemViewType(holder.getAdapterPosition())) {
            setLoadMoreViewParams(holder);
        } else {
            mInnerAdapter.onViewAttachedToWindow(holder);
        }
        */
        mInnerAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        /*
        if (TYPE_LOADMORE_VIEW == getItemViewType(holder.getLayoutPosition())) {
            setLoadMoreViewParams(holder);
        } else {
            mInnerAdapter.onViewDetachedFromWindow(holder);
        }
        */
        mInnerAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        /*
        if (TYPE_LOADMORE_VIEW == getItemViewType(holder.getLayoutPosition())) {
            setLoadMoreViewParams(holder);
        } else {
            mInnerAdapter.onViewRecycled(holder);
        }
        */
        mInnerAdapter.onViewRecycled(holder);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mInnerAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInnerAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInnerAdapter.unregisterAdapterDataObserver(observer);
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }
}
