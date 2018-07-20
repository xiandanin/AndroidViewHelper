package com.dyhdyh.helper.recyclerview.example.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dyhdyh.helper.recyclerview.example.R;
import com.dyhdyh.helper.recyclerview.example.SampleCoverVideo;
import com.dyhdyh.helper.recyclerview.example.model.ExampleData;

import java.util.List;

/**
 * @author dengyuhan
 * @created 2017/12/4 18:12
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ExampleHolder> {
    private List<ExampleData> data;

    public VideoAdapter(List<ExampleData> data) {
        this.data = data;
    }

    @Override
    public ExampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExampleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(ExampleHolder holder, int position) {
        holder.placeholder.setVisibility(position == 0 ? View.VISIBLE : View.GONE);

        ExampleData data = this.data.get(position);
        holder.tv.setText(position + " - " + data.getTitle());
        holder.tv_name.setText(data.getNickname());
        Glide.with(holder.itemView.getContext()).load(data.getImage()).into(holder.ivAvatar);
        //holder.player.getLayoutParams().height = data.getDisplayHeight();

        holder.player.setUp(data.getFileUrl(), true, "测试视频");

        holder.player.loadCoverImage(data.getFileUrl(), data.getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ExampleHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView tv_name;
        ImageView ivAvatar;
        SampleCoverVideo player;
        View placeholder;

        public ExampleHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            player = itemView.findViewById(R.id.video);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tv_name = itemView.findViewById(R.id.tv_name);
            placeholder = itemView.findViewById(R.id.placeholder);
        }
    }
}
