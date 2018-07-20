package com.dyhdyh.helper.recyclerview.viewvisible;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author dengyuhan
 * created 2017/12/4 18:00
 */
public class RecyclerViewVisibleHelper {
    private int mFirstVisibleItem;
    private int mLastVisibleItem;
    private int mVisibleCount;

    private ViewVisibleCallback mCallback;

    public RecyclerViewVisibleHelper(ViewVisibleCallback callback) {
        this.mCallback = callback;
    }

    public void onScrolled(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        mFirstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        mLastVisibleItem = layoutManager.findLastVisibleItemPosition();
        mVisibleCount = mLastVisibleItem - mFirstVisibleItem + 1;
    }


    public List<ViewVisibleInfo> getLocalVisibleInfo(RecyclerView recyclerView) {
        final boolean vertical = isVertical(recyclerView);
        return getLocalVisibleInfo(recyclerView, new Comparator<ViewVisibleInfo>() {
            @Override
            public int compare(ViewVisibleInfo o1, ViewVisibleInfo o2) {
                float o1VisiblePercent = vertical ? o1.getVisibleHeightPercent() : o1.getVisibleWidthPercent();
                float o2VisiblePercent = vertical ? o2.getVisibleHeightPercent() : o2.getVisibleWidthPercent();
                float o1VisiblePercentParent = vertical ? o1.getVisibleHeightPercentByParent() : o1.getVisibleWidthPercentByParent();
                float o2VisiblePercentParent = vertical ? o2.getVisibleHeightPercentByParent() : o2.getVisibleWidthPercentByParent();
                if (o1VisiblePercent == o2VisiblePercent) {
                    return o1VisiblePercentParent > o2VisiblePercentParent ? -1: 1;
                } else {
                    return o1VisiblePercent > o2VisiblePercent ? -1 : 1;
                }
            }
        });
    }

    public List<ViewVisibleInfo> getLocalVisibleInfo(RecyclerView recyclerView, Comparator<ViewVisibleInfo> comparator) {
        List<ViewVisibleInfo> infoList = new ArrayList<>();

        for (int i = 0; i < mVisibleCount; i++) {
            View itemView = recyclerView.getChildAt(i);
            int position = mFirstVisibleItem + i;

            if (mCallback != null) {
                View itemTargetView = mCallback.getItemTargetView(itemView);
                if (itemTargetView != null) {
                    Rect rect = new Rect();
                    boolean isVisible = itemTargetView.getLocalVisibleRect(rect);
                    int visibleHeight = rect.bottom - rect.top;
                    int visibleWidth = rect.right - rect.left;
                    float visibleHeightPercent = (float) visibleHeight / itemTargetView.getHeight();
                    float visibleWidthPercent = (float) visibleWidth / itemTargetView.getWidth();
                    float visibleHeightPercentParent = (float) visibleHeight / recyclerView.getHeight();
                    float visibleWidthPercentParent = (float) visibleWidth / recyclerView.getWidth();

                    ViewVisibleInfo info = new ViewVisibleInfo();
                    info.setPosition(position);
                    info.setItemView(itemView);
                    info.setVisible(isVisible);
                    info.setRect(rect);
                    if (isVisible) {
                        info.setVisibleWidthPercent(visibleWidthPercent);
                        info.setVisibleHeightPercent(visibleHeightPercent);
                        info.setVisibleWidthPercentByParent(visibleWidthPercentParent);
                        info.setVisibleHeightPercentByParent(visibleHeightPercentParent);
                    }

                    infoList.add(info);
                }
            }
        }

        if (comparator != null) {
            Collections.sort(infoList, comparator);
        }
        return infoList;
    }

    protected boolean isVertical(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int orientation = OrientationHelper.VERTICAL;
        if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return OrientationHelper.VERTICAL == orientation;
    }

    public interface ViewVisibleCallback {

        /**
         * 获取需要计算的View
         *
         * @param itemView
         * @return
         */
        View getItemTargetView(View itemView);
    }
}
