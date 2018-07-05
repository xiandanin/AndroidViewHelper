package com.dyhdyh.helper.recyclerview.loadmore.valyout;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dyhdyh.helper.recyclerview.loadmore.LoadMoreDelegateAdapter;
import com.dyhdyh.helper.recyclerview.loadmore.LoadMoreView;

/**
 * @author dengyuhan
 *         created 2018/7/3 16:32
 */
public class LoadMoreVLayoutAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> implements LoadMoreDelegateAdapter.Delegate {

    private LoadMoreDelegateAdapter mDelegateAdapter;

    public LoadMoreVLayoutAdapter() {
        this(null);
    }

    public LoadMoreVLayoutAdapter(LoadMoreView loadMoreView) {
        this.mDelegateAdapter = new LoadMoreDelegateAdapter(loadMoreView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateAdapter.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegateAdapter.onBindViewHolder(holder);
    }


    @Override
    public void setLoadMoreState(int state) {
        mDelegateAdapter.setLoadMoreState(state);
        notifyDataSetChanged();
    }

    @Override
    public int getLoadMoreState() {
        return mDelegateAdapter.getLoadMoreState();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }
}
