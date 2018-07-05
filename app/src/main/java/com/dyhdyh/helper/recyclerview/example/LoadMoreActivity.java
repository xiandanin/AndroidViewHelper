package com.dyhdyh.helper.recyclerview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyhdyh.helper.recyclerview.loadmore.LoadMoreHelper;
import com.dyhdyh.helper.recyclerview.loadmore.LoadMoreView;
import com.dyhdyh.helper.recyclerview.loadmore.OnLoadMoreListener;
import com.dyhdyh.helper.recyclerview.loadmore.valyout.LoadMoreVLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreActivity extends AppCompatActivity {
    RecyclerView rv;

    SimpleAdapter mAdapter;

    LoadMoreVLayoutAdapter mLoadMoreAdapter;

    private LoadMoreHelper mLoadMoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        rv = (RecyclerView) findViewById(R.id.rv);


        /*
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mAdapter = new SimpleAdapter(testData());
        delegateAdapter.addAdapter(mAdapter);
        mLoadMoreAdapter = LoadMoreHelper.buildVLayoutAdapter();
        delegateAdapter.addAdapter(mLoadMoreAdapter);
        rv.setAdapter(delegateAdapter);
        */
        mLoadMoreHelper = new LoadMoreHelper(rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SimpleAdapter(testData());
        mLoadMoreHelper.setAdapter(mAdapter);

        mLoadMoreHelper.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (LoadMoreView.THE_END == mLoadMoreHelper.getLoadMoreState()) {
                    return;
                }
                mLoadMoreHelper.setLoadMoreState(LoadMoreView.LOADING);
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addAll(testData());
                        mLoadMoreHelper.setLoadMoreCompleted();
                        mLoadMoreHelper.setLoadMoreState(LoadMoreView.THE_END);
                    }
                }, 2000);
            }
        });

    }

    private List<String> testData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            data.add("Item " + i);
        }
        return data;
    }
}
