package com.dyhdyh.helper.recyclerview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.dyhdyh.helper.recyclerview.example.adapter.SimpleAdapter;
import com.dyhdyh.helper.recyclerview.loadmore.SimpleRecyclerView;
import com.dyhdyh.subscriber.rxjava2.RxJava2Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class LoadMoreActivity extends AppCompatActivity {
    SimpleRecyclerView rv;

    SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        rv = findViewById(R.id.rv);


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

        asyncTestData();


        rv.setOnRefreshListener(new SimpleRecyclerView.OnRefreshListener2() {
            @Override
            public void onRefresh(final boolean refresh) {
                if (refresh) {
                    rv.resetPage();
                }
                asyncTestData();
            }
        });
    }


    private void asyncTestData() {
        asyncTestDataObservable(rv.getPage())
.subscribe(new TestPagingObserver(findViewById(R.id.content_view), rv, "出错了") {
    @Override
    protected void onRefreshResponse(List<String> response, List<String> list) {
        rv.setLayoutManager(new LinearLayoutManager(LoadMoreActivity.this));
        mAdapter = new SimpleAdapter(response);
        rv.setAdapter(mAdapter);
    }

    @Override
    protected void onAppendResponse(List<String> response, List<String> list) {
        mAdapter.addAll(response);
    }

    @Override
    protected boolean isLast(List<String> response) {
        //假设第三页就是最后一页
        return rv.getPage() == 3;
    }

    @Override
    public void onClickErrorLayout(View errorLayout) {
        asyncTestData();
    }
});

    }

    private Observable<List<String>> asyncTestDataObservable(int page) {
        Toast.makeText(LoadMoreActivity.this, "第" + page + "页", Toast.LENGTH_SHORT).show();
        return RxJava2Observable.async(new Function<Void, List<String>>() {
            @Override
            public List<String> apply(Void aVoid) throws Exception {
                Thread.sleep(1500);
                if (new Random().nextBoolean()) {
                    throw new Exception("随机错误");
                }
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 22; i++) {
                    data.add("Item " + i);
                }
                return data;
            }
        });
    }

}
