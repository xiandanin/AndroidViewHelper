package com.dyhdyh.helper.viewhelper;

import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;

/**
 * Material Design
 *
 * @author dengyuhan
 *         created 2018/7/20 15:58
 */
public class DesignViewHelper {


    /**
     * 设置SwipeRefreshLayout颜色
     *
     * @param refreshLayout
     * @param colorRes
     */
    public static void setSwipeRefreshLayoutColor(SwipeRefreshLayout refreshLayout, @ColorRes int... colorRes) {
        int[] colorArray = new int[colorRes.length];
        for (int i = 0; i < colorRes.length; i++) {
            colorArray[i] = refreshLayout.getContext().getResources().getColor(colorRes[i]);
        }
        refreshLayout.setColorSchemeColors(colorArray);
    }

    /**
     * 设置SwipeRefreshLayout为主题色
     *
     * @param refreshLayout
     */
    public static void setSwipeRefreshLayoutPrimary(SwipeRefreshLayout refreshLayout) {
        TypedValue typedValue = new TypedValue();
        int res = android.support.v7.appcompat.R.attr.colorPrimary;
        refreshLayout.getContext().getTheme().resolveAttribute(res, typedValue, true);
        int colorPrimary = typedValue.data;
        refreshLayout.setColorSchemeColors(colorPrimary);
    }

    /**
     * SwipeRefreshLayout嵌套AppBarLayout
     *
     * @param refreshLayout
     * @param appBarLayout
     */
    public static void nestedSwipeAppBar(final SwipeRefreshLayout refreshLayout, AppBarLayout appBarLayout) {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                refreshLayout.setEnabled(verticalOffset >= 0);
            }
        });
    }

    /**
     * SwipeRefreshLayout嵌套ViewPager
     *
     * @param refreshLayout
     * @param viewPager
     */
    public static void nestedSwipeViewPager(final SwipeRefreshLayout refreshLayout, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                refreshLayout.setEnabled(positionOffset == 0f);
            }
        });
    }

    /**
     * 滚动RecyclerView
     *
     * @param recyclerView
     * @param position
     * @param offset
     */
    public static void scrollToPositionWithOffset(RecyclerView recyclerView, int position, int offset) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(position, offset);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).scrollToPositionWithOffset(position, offset);
        }
    }


    /**
     * 滚动appbar
     * 只能向上滚 不能向下滚
     *
     * @param appBar
     * @param behaviorView
     * @param dy
     */
    public static void scrollAppBarBy(AppBarLayout appBar, View behaviorView, int dx, int dy) {
        android.support.design.widget.CoordinatorLayout.Behavior behavior = ((android.support.design.widget.CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
        if (behavior != null) {
            ViewParent parent = appBar.getParent();
            if (parent instanceof CoordinatorLayout) {
                behavior.onNestedScroll((CoordinatorLayout) parent, appBar, behaviorView, dx, dy, dx, dy, ViewCompat.TYPE_TOUCH);
                //behavior.onNestedPreScroll((CoordinatorLayout) parent,appBar,behaviorView,0,dy,new int[]{0,0});
            }
            if (behaviorView instanceof RecyclerView) {
                ((RecyclerView) behaviorView).scrollToPosition(0);
            }
        }
    }

    /**
     * appbar滚到顶
     *
     * @param appBar
     * @param behaviorView
     */
    public static void scrollAppBarTop(AppBarLayout appBar, View behaviorView) {
        scrollAppBarBy(appBar, behaviorView, 0, appBar.getTotalScrollRange());
    }


    public static void scrollToY(final NestedScrollView sv, final int y) {
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.scrollTo(0, y);
            }
        });
    }
}
