package com.dyhdyh.helper.recyclerview.viewvisible;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * 自动播放
 *
 * @author dengyuhan
 * created 2017/12/4 18:01
 */
public class OnAutoPlayScrollListener extends AbstractViewVisibleScrollListener {

    public OnAutoPlayScrollListener(RecyclerViewVisibleHelper.ViewVisibleCallback callback) {
        super(callback);
    }

    public OnAutoPlayScrollListener(RecyclerViewVisibleHelper helper) {
        super(helper);
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (RecyclerView.SCROLL_STATE_IDLE == newState) {
            List<ViewVisibleInfo> infoList = mHelper.getLocalVisibleInfo(recyclerView);
            dispatcherVisibleInfoList(infoList);
        }
    }

    protected void dispatcherVisibleInfoList(List<ViewVisibleInfo> infoList) {
        if (!infoList.isEmpty()) {
            ViewVisibleInfo info = infoList.get(0);
            startPlay(info.getItemView());
        }
    }


    public void startPlay(View itemView) {

    }
}
