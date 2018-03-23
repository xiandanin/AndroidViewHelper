package com.dyhdyh.helper.checkable.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.dyhdyh.helper.checkable.SingleCheckableAdapter;
import com.dyhdyh.helper.checkable.SingleCheckableHelper;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:46
 */
public class SingleExampleAdapter extends BaseRecyclerAdapter<ExampleModel,SingleExampleAdapter.Holder> implements SingleCheckableAdapter {

    private SingleCheckableHelper mSingleHelper;

    public SingleExampleAdapter(List<ExampleModel> data) {
        super(data);
        mSingleHelper = new SingleCheckableHelper(this);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position, ExampleModel item) {
        holder.tv_item_example.setText(item.getLabel());
        holder.ctv_item_example.setChecked(item.isChecked());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingleHelper.setCheckedPosition(position);
            }
        });
    }

    @Override
    public void onChecked(int mode, int checkedPosition, boolean checked) {
        getData().get(checkedPosition).setChecked(checked);
        notifyItemChanged(checkedPosition);
    }

    @Override
    public void clear() {
        mSingleHelper.clear();
    }

    @Override
    public void setCheckedPosition(Integer checkedPosition) {
        mSingleHelper.setCheckedPosition(checkedPosition);
    }

    @Override
    public Integer getCheckedPosition() {
        return mSingleHelper.getCheckedPosition();
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
