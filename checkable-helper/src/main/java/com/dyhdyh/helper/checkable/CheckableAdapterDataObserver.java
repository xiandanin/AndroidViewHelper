package com.dyhdyh.helper.checkable;

import android.support.v7.widget.RecyclerView;

/**
 * @author dengyuhan
 *         created 2018/3/23 16:34
 */
public class CheckableAdapterDataObserver extends RecyclerView.AdapterDataObserver {
    private RecyclerView.AdapterDataObserver mInnerObserver;

    public CheckableAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        this.mInnerObserver = observer;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        this.mInnerObserver.onChanged();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
        this.mInnerObserver.onItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
        this.mInnerObserver.onItemRangeChanged(positionStart, itemCount, payload);
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        this.mInnerObserver.onItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        this.mInnerObserver.onItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        this.mInnerObserver.onItemRangeMoved(fromPosition, toPosition, itemCount);
    }
}
