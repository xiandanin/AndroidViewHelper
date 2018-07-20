package com.dyhdyh.helper.recyclerview.example;

import android.view.View;

import com.dyhdyh.helper.recyclerview.subscribers.PagingViewController;
import com.dyhdyh.helper.recyclerview.subscribers.SimplePagingObserver;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/7/20 14:45
 */
public abstract class TestPagingObserver extends SimplePagingObserver<List<String>, String> {
    public TestPagingObserver(View parent, PagingViewController controller) {
        super(parent, controller);
    }

    public TestPagingObserver(View parent, PagingViewController controller, CharSequence errorMessage) {
        super(parent, controller, errorMessage);
    }

    @Override
    protected abstract void onRefreshResponse(List<String> response, List<String> list);

    @Override
    protected abstract boolean isLast(List<String> response);

    @Override
    protected List<String> getPagingListData(List<String> response) {
        return response;
    }

}
