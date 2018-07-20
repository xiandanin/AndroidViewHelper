package com.dyhdyh.helper.recyclerview.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAutoPlay(View view) {
        startActivity(new Intent(this, VisibleActivity.class));
    }

    public void clickLoadMore(View view) {
        startActivity(new Intent(this, LoadMoreActivity.class));
    }

    public void clickChecked(View view) {
        startActivity(new Intent(this, CheckedActivity.class));
    }
}
