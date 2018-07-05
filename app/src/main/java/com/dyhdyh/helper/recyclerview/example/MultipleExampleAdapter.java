package com.dyhdyh.helper.recyclerview.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.dyhdyh.helper.recyclerview.checkable.MultipleCheckableAdapter;
import com.dyhdyh.helper.recyclerview.checkable.MultipleCheckableHelper;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:46
 */
public class MultipleExampleAdapter extends BaseRecyclerAdapter<ExampleModel, MultipleExampleAdapter.Holder> implements MultipleCheckableAdapter<ExampleModel> {

    private MultipleCheckableHelper<ExampleModel> mMultipleHelper;

    public MultipleExampleAdapter(List<ExampleModel> data) {
        super(data);
        mMultipleHelper = new MultipleCheckableHelper(this);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position, final ExampleModel item) {
        holder.tv_item_example.setText(item.getLabel());
        holder.ctv_item_example.setChecked(item.isChecked());
    }

    @Override
    public void onAdapterNotifyChanged(int[] checkedPositionArray) {
        notifyDataSetChanged();
    }

    @Override
    public void onChecked(int checkedPosition, boolean checked) {
        getData().get(checkedPosition).setChecked(checked);
    }

    @Override
    public void clear() {
        mMultipleHelper.clear();
    }


    @Override
    public ExampleModel getItem(int position) {
        return getData().get(position);
    }


    @Override
    public void setCheckedPositionArray(int[] checkedPositionArray, boolean checked) {
        mMultipleHelper.setCheckedPositionArray(checkedPositionArray, checked);
    }

    @Override
    public int[] getCheckedPositionArray() {
        return mMultipleHelper.getCheckedPositionArray();
    }

    @Override
    public List<ExampleModel> getCheckedList() {
        return mMultipleHelper.getCheckedList();
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
