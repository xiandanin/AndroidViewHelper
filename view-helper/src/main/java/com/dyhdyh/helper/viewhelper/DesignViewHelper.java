package com.dyhdyh.helper.viewhelper;

import android.animation.ValueAnimator;
import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.dyhdyh.helper.viewhelper.listener.OnNestedAppBarViewPagerListener;

import java.lang.reflect.Method;

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
     * SwipeRefreshLayout同时嵌套appbar和ViewPager
     *
     * @param refreshLayout
     * @param appBarLayout
     * @param viewPager
     */
    public static void nestedSwipeAppBarViewPager(final SwipeRefreshLayout refreshLayout, AppBarLayout appBarLayout, ViewPager viewPager) {
        NestedAppBarViewPagerHelper helper = new NestedAppBarViewPagerHelper(appBarLayout, viewPager);
        helper.setNestedListener(new OnNestedAppBarViewPagerListener() {
            @Override
            public void onNestedChanged(AppBarLayout appBarLayout, int verticalOffset, ViewPager viewPager, float positionOffset) {
                refreshLayout.setEnabled(verticalOffset >= 0 && positionOffset == 0);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                helper.setVerticalOffset(verticalOffset);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                helper.setPositionOffset(positionOffset);
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
     * 设置appbar偏移量
     *
     * @param appBar
     * @param offset
     */
    public static void setAppBarLayoutOffset(AppBarLayout appBar, int offset) {
        ViewGroup.LayoutParams params = appBar.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            try {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
                Class cls = Class.forName("android.support.design.widget.HeaderBehavior");
                //滑到指定位置
                Method animateOffsetTo = cls.getDeclaredMethod("setHeaderTopBottomOffset", CoordinatorLayout.class, View.class, int.class);
                animateOffsetTo.setAccessible(true);
                animateOffsetTo.invoke(behavior, appBar.getParent(), appBar, offset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 带动画的设置设置appbar偏移
     *
     * @param appBar
     * @param offset
     * @param duration
     */
    public static void setAppBarLayoutOffsetWithAnimate(AppBarLayout appBar, int offset, long duration) {
        ViewGroup.LayoutParams params = appBar.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                final int currentOffset = ((AppBarLayout.Behavior) behavior).getTopAndBottomOffset();

                final ValueAnimator animator = ValueAnimator.ofInt(currentOffset, offset)
                        .setDuration(duration);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        final int value = (int) animation.getAnimatedValue();
                        setAppBarLayoutOffset(appBar, value);
                    }
                });
                animator.start();
            }

        }
    }

    /**
     * appbar滚到顶
     *
     * @param appBar
     */
    public static void scrollAppBarTop(AppBarLayout appBar, boolean animate) {
        if (animate) {
            setAppBarLayoutOffsetWithAnimate(appBar, 0, 600);
        } else {
            setAppBarLayoutOffset(appBar, 0);
        }
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
