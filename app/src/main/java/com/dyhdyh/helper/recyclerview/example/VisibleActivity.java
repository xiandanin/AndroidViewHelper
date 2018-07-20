package com.dyhdyh.helper.recyclerview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dyhdyh.helper.recyclerview.example.adapter.VideoAdapter;
import com.dyhdyh.helper.recyclerview.example.model.ExampleData;
import com.dyhdyh.helper.recyclerview.viewvisible.OnAutoPlayScrollListener;
import com.dyhdyh.helper.recyclerview.viewvisible.RecyclerViewVisibleHelper;
import com.dyhdyh.helper.recyclerview.viewvisible.ViewVisibleInfo;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VisibleActivity extends AppCompatActivity {
    RecyclerView rv;
    TextView tv_log;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        rv = findViewById(R.id.rv);
        tv_log = findViewById(R.id.tv_log);

        File file = new File(getCacheDir(), "test.mp4");
        try {
            copyFile(getAssets().open("test.mp4"), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        VideoAdapter adapter = new VideoAdapter(ExampleData.randomData(30, file.getAbsolutePath()));
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new OnAutoPlayScrollListener(
                new RecyclerViewVisibleHelper.ViewVisibleCallback() {
                    @Override
                    public View getItemTargetView(View itemView) {
                        return itemView.findViewById(R.id.video);
                    }
                }) {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                List<ViewVisibleInfo> infoList = mHelper.getLocalVisibleInfo(recyclerView);

                StringBuffer sb = new StringBuffer();
                for (ViewVisibleInfo info : infoList) {
                    sb.append(info.getPosition());
                    sb.append(" | ");

                    if (info.isVisible()) {
                        sb.append(info.getVisibleHeightPercent() == 1 ? "完全可见" : "部分可见");
                    } else {
                        sb.append("不可见");
                    }
                    sb.append(" | ");
                    sb.append(info.getVisibleHeightPercent());
                    sb.append(" | ");
                    sb.append(info.getVisibleHeightPercentByParent());
                    sb.append("\n");
                }
                tv_log.setText(sb.toString());
            }

            @Override
            public void startPlay(View itemView) {
                StandardGSYVideoPlayer player = itemView.findViewById(R.id.video);
                if (player != null && player.getCurrentState() != GSYVideoView.CURRENT_STATE_PLAYING) {
                    player.setVisibility(View.VISIBLE);
                    player.startPlayLogic();
                }
            }
        });
    }


    public void copyFile(InputStream stream, File newPath) {
        try {
            int byteRead = 0;
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1024];
            while ((byteRead = stream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            fs.close();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
