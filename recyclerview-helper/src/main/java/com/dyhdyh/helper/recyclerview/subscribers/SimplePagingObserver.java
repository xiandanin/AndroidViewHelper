package com.dyhdyh.helper.recyclerview.subscribers;

import android.view.View;

import com.dyhdyh.subscriber.handler.ErrorHandler;
import com.dyhdyh.subscriber.handler.LoadingHandler;
import com.dyhdyh.subscribers.loadingbar.handler.SimpleToastErrorHandler;
import com.dyhdyh.subscribers.loadingbar.rxjava2.SimpleLoadingBarObserver;
import com.dyhdyh.widget.loading.bar.LoadingBar;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/7/20 14:11
 */
public abstract class SimplePagingObserver<T, Item> extends SimpleLoadingBarObserver<T> {
    protected PagingViewController mController;
    private ErrorHandler<CharSequence> mRefreshErrorHandler;


    public SimplePagingObserver(View parent, PagingViewController controller) {
        this(parent, controller, null);
    }

    public SimplePagingObserver(View parent, PagingViewController controller, CharSequence errorMessage) {
        super(parent, errorMessage);
        this.mController = controller;
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        dispatchPaging(t);
    }

    @Override
    public void showLoading(CharSequence params, LoadingHandler<CharSequence> handler) {
        if (mController.isUseInitLoading()) {
            //首次加载 用loadingbar
            super.showLoading(params, handler);
        } else {
            mController.setLoadMore();
        }
    }

    @Override
    public void cancelLoading(LoadingHandler<CharSequence> handler) {
        super.cancelLoading(handler);
        mController.setCompleted();
    }

    @Override
    public void showError(CharSequence errorParams, Throwable e, ErrorHandler<CharSequence> handler) {
        if (mController.isUseInitLoading()) {
            //首次加载错误 用loadingbar
            super.showError(errorParams, e, handler);
        } else {
            mController.setUseInitLoading(false);
            //如果是第一页
            if (isFirst()) {
                showRefreshError(errorParams, e, getRefreshErrorHandler());
            } else {
                mController.setLoadMoreError(errorParams);
            }
        }
    }

    /**
     * 刷新时的错误回调
     *
     * @param errorParams
     * @param e
     * @param handler
     */
    public void showRefreshError(CharSequence errorParams, Throwable e, ErrorHandler<CharSequence> handler) {
        if (handler != null) {
            handler.showError(errorParams, e);
        }
    }

    /**
     * 分发成功后的处理
     *
     * @param response
     */
    protected void dispatchPaging(T response) {
        mController.setUseInitLoading(false);

        final List<Item> listData = getPagingListData(response);
        //如果是第一页
        if (isFirst()) {
            if (listData.isEmpty()) {
                onEmptyPaging();
            } else {
                onCancelEmptyPaging();
            }

            onRefreshResponse(response, listData);

            //第一页也有可能是最后一页
            if (isLast(response)) {
                mController.setTheEnd();
            } else {
                mController.nextPage();
            }
        } else {
            //如果空的就是最后一页
            if (listData.isEmpty()) {
                //加载更多设成已经结束
                mController.setTheEnd();
            } else {
                //不是空的就先加上数据
                onAppendResponse(response, listData);

                //再看这页是不是最后一页
                if (isLast(response)) {
                    mController.setTheEnd();
                } else {
                    //不是最后一页 就加一页
                    mController.nextPage();
                }
            }
        }
    }

    /**
     * 集合没有数据的时候
     */
    protected void onEmptyPaging() {

    }

    protected void onCancelEmptyPaging() {
        LoadingBar.cancel(getEmptyLayoutParent());
    }

    protected View getEmptyLayoutParent() {
        final View contentLayout = mController.getEmptyParentLayout();
        return contentLayout == null ? mParent : contentLayout;
    }

    /**
     * 刷新
     * @param response
     * @param list
     */
    protected abstract void onRefreshResponse(T response, List<Item> list);

    /**
     * 加载更多
     * @param response
     * @param list
     */
    protected void onAppendResponse(T response, List<Item> list) {

    }


    protected boolean isFirst() {
        return mController.isFirst();
    }

    protected abstract boolean isLast(T response);

    protected abstract List<Item> getPagingListData(T response);

    @Override
    public void onClickErrorLayout(View errorLayout) {
        mController.setUseInitLoading(true);
    }

    public ErrorHandler<CharSequence> getRefreshErrorHandler() {
        if (mRefreshErrorHandler == null) {
            mRefreshErrorHandler = this.createRefreshErrorHandler();
        }
        return mRefreshErrorHandler;
    }

    /**
     * 刷新时错误的Handler
     *
     * @return
     */
    public ErrorHandler<CharSequence> createRefreshErrorHandler() {
        return new SimpleToastErrorHandler(mParent.getContext());
    }

}
