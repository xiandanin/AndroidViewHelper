package com.dyhdyh.helper.recyclerview.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyhdyh.helper.recyclerview.example.adapter.SimpleAdapter;
import com.dyhdyh.helper.viewhelper.DesignViewHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesignViewActivity extends AppCompatActivity {
    EditText ed_scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_view);
        ed_scroll = findViewById(R.id.ed_scroll);
        RecyclerView recyclerView = findViewById(R.id.rv_example);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Content Item " + i);
        }
        recyclerView.setAdapter(new SimpleAdapter(data));


        ViewGroup header = findViewById(R.id.layout_appbar_header);
        final Random random = new Random();
        int itemPadding = getResources().getDimensionPixelSize(R.dimen.padding_item);
        int totalHeight = 0;
        for (int i = 0; i < 20; i++) {
            TextView item = new TextView(this);
            int itemHeight = (random.nextInt(20) + 10) * 10;
            final int oldTotalHeight = totalHeight;
            totalHeight += itemHeight;
            item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, itemHeight));
            item.setBackgroundColor(Color.rgb(random.nextInt(125) + 125, random.nextInt(125) + 125, random.nextInt(125) + 125));
            item.setPadding(0, itemPadding, 0, itemPadding);
            item.setGravity(Gravity.CENTER);
            item.setTextColor(Color.WHITE);
            item.setText("Item" + i + " 位置 : " + oldTotalHeight + "px - " + totalHeight + "px");
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ed_scroll.setText(String.valueOf(oldTotalHeight));
                }
            });
            header.addView(item);
        }

    }

    public void clickDesignViewY(View view) {
        AppBarLayout appBarLayout = findViewById(R.id.appbar_layout);
        CheckBox anim = findViewById(R.id.cb_anim);
        //负数往下
        final int y = -Integer.parseInt(ed_scroll.getText().toString());
        if (anim.isChecked()) {
            DesignViewHelper.setAppBarLayoutOffsetWithAnimate(appBarLayout, y, 300);
        } else {
            DesignViewHelper.setAppBarLayoutOffset(appBarLayout, y);
        }
    }
}
