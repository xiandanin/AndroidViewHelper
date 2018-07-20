package com.dyhdyh.helper.recyclerview.subscribers;

import android.view.View;

/**
 * @author dengyuhan
 *         created 2018/7/20 14:16
 */

public interface PagingViewController {
    boolean isFirst();

    View getEmptyParentLayout();

    /**
     * @return 是否是使用初始化用的loading
     */
    boolean isUseInitLoading();

    void setUseInitLoading(boolean useInitLoading);

    void setCompleted();

    void setLoadMoreError(CharSequence errorMessage);

    void setLoadMore();

    void setTheEnd();

    void nextPage();
}
