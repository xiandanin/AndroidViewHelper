package com.dyhdyh.helper.recyclerview.viewvisible;

import android.support.v7.widget.RecyclerView;

/**
 * @author dengyuhan
 * created 2017/12/5 15:36
 */
public abstract class AbstractViewVisibleScrollListener extends RecyclerView.OnScrollListener {
    protected RecyclerViewVisibleHelper mHelper;

    public AbstractViewVisibleScrollListener(RecyclerViewVisibleHelper.ViewVisibleCallback callback) {
        this.mHelper = new RecyclerViewVisibleHelper(callback);
    }

    public AbstractViewVisibleScrollListener(RecyclerViewVisibleHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mHelper.onScrolled(recyclerView);
    }


}
