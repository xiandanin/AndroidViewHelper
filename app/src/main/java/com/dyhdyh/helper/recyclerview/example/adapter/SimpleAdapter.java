package com.dyhdyh.helper.recyclerview.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dyhdyh.helper.recyclerview.example.R;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/7/3 16:42
 */
public class SimpleAdapter extends DelegateAdapter.Adapter<SimpleAdapter.ItemHolder> {
    private List<String> mData;

    public SimpleAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        holder.tv.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getData().size() - position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<String> getData() {
        return mData;
    }

    public void addAll(List<String> list) {
        final int size = mData.size();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ItemHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
