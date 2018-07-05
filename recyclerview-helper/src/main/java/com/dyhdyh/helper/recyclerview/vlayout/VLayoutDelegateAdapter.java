package com.dyhdyh.helper.recyclerview.vlayout;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/7/4 10:23
 */
public abstract class VLayoutDelegateAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter mInternalAdapter;

    public VLayoutDelegateAdapter(RecyclerView.Adapter adapter) {
        this.mInternalAdapter = adapter;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        mInternalAdapter.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mInternalAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mInternalAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mInternalAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mInternalAdapter.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return mInternalAdapter.getItemId(position);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mInternalAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInternalAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mInternalAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInternalAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mInternalAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mInternalAdapter.onViewRecycled(holder);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mInternalAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInternalAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mInternalAdapter.unregisterAdapterDataObserver(observer);
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInternalAdapter;
    }
}
