package com.dyhdyh.helper.checkable.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.dyhdyh.helper.checkable.MultipleCheckableAdapter;
import com.dyhdyh.helper.checkable.MultipleCheckableHelper;
import com.dyhdyh.helper.checkable.SingleCheckableAdapter;
import com.dyhdyh.helper.checkable.SingleCheckableHelper;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:46
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Holder> implements SingleCheckableAdapter, MultipleCheckableAdapter<ExampleModel> {
    private List<ExampleModel> mData;

    private SingleCheckableHelper mSingleHelper;
    private MultipleCheckableHelper<ExampleModel> mMultipleAdapter;

    public ExampleAdapter(List<ExampleModel> data) {
        this.mData = data;
        mSingleHelper = new SingleCheckableHelper(this);
        mMultipleAdapter = new MultipleCheckableHelper<>(this);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final ExampleModel model = mData.get(position);
        holder.tv_item_example.setText(model.getLabel());
        holder.ctv_item_example.setChecked(model.isChecked());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckedPositionArray(new int[]{position},!model.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onChecked(int checkedPosition, boolean checked) {
        mData.get(checkedPosition).setChecked(checked);
        notifyItemChanged(checkedPosition);
    }

    @Override
    public void setCheckedPosition(int checkedPosition) {
        mSingleHelper.setCheckedPosition(checkedPosition);
    }

    @Override
    public int getCheckedPosition() {
        return mSingleHelper.getCheckedPosition();
    }

    @Override
    public void onChecked(List<ExampleModel> checkedData, int checkedPosition, boolean checked) {
        ExampleModel item = mData.get(checkedPosition);
        item.setChecked(checked);
        if (checked) {
            checkedData.add(item);
        } else {
            checkedData.remove(item);
        }
        notifyItemChanged(checkedPosition);
    }

    @Override
    public void setCheckedPositionArray(int[] checkedPositionArray, boolean checked) {
        mMultipleAdapter.setCheckedPositionArray(checkedPositionArray, checked);
    }

    @Override
    public List<Integer> getCheckedPositionList() {
        return null;
    }

    @Override
    public List<ExampleModel> getCheckedData() {
        return mMultipleAdapter.getCheckedData();
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
