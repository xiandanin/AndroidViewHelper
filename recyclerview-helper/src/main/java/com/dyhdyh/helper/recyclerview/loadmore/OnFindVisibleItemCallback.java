package com.dyhdyh.helper.recyclerview.loadmore;

import android.support.v7.widget.RecyclerView;

/**
 * @author dengyuhan
 *         created 2018/8/17 16:11
 */
public interface OnFindVisibleItemCallback {
    int findFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager);

    int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager);
}
