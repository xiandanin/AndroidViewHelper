package com.dyhdyh.helper.checkable.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.dyhdyh.helper.checkable.CheckableAdapter;
import com.dyhdyh.helper.checkable.SingleCheckableHelper;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:46
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Holder> implements CheckableAdapter {
    private List<ExampleModel> mData;

    private SingleCheckableHelper mHelper;

    public ExampleAdapter(List<ExampleModel> data) {
        this.mData = data;
        mHelper = new SingleCheckableHelper(this);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        ExampleModel model = mData.get(position);
        holder.tv_item_example.setText(model.getLabel());
        holder.ctv_item_example.setChecked(model.isChecked());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckedPosition(position);
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
        mHelper.setCheckedPosition(checkedPosition);
    }

    @Override
    public int getCheckedPosition() {
        return mHelper.getCheckedPosition();
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
