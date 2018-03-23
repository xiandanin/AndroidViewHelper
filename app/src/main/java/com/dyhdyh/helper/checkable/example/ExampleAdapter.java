package com.dyhdyh.helper.checkable.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:46
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Holder> {
    private List<ExampleModel> mData;

    public ExampleAdapter(List<ExampleModel> data) {
        this.mData = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ExampleModel model = mData.get(position);
        holder.tv_item_example.setText(model.getLabel());
        holder.ctv_item_example.setChecked(model.isChecked());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView tv_item_example;
        CheckedTextView ctv_item_example;

        public Holder(View itemView) {
            super(itemView);
            tv_item_example = itemView.findViewById(R.id.tv_item_example);
            ctv_item_example = itemView.findViewById(R.id.ctv_item_example);
        }
    }
}
