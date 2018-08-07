package com.dyhdyh.helper.viewhelper;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;

import com.dyhdyh.helper.viewhelper.listener.OnNestedAppBarViewPagerListener;

/**
 * @author dengyuhan
 *         created 2018/8/7 16:30
 */
public final class NestedAppBarViewPagerHelper {

    private AppBarLayout mAppBarLayout;
    private int mVerticalOffset;

    private ViewPager mViewPager;
    private float mPositionOffset;

    private OnNestedAppBarViewPagerListener mNestedListener;


    public NestedAppBarViewPagerHelper(AppBarLayout appBarLayout, ViewPager viewPager) {
        this.mAppBarLayout = appBarLayout;
        this.mViewPager = viewPager;
    }

    public void setNestedListener(OnNestedAppBarViewPagerListener listener) {
        this.mNestedListener = listener;
    }

    public void setVerticalOffset(int verticalOffset) {
        this.mVerticalOffset = verticalOffset;
        if (mNestedListener != null) {
            mNestedListener.onNestedChanged(mAppBarLayout, mVerticalOffset, mViewPager, mPositionOffset);
        }
    }

    public void setPositionOffset(float positionOffset) {
        this.mPositionOffset = positionOffset;
        if (mNestedListener != null) {
            mNestedListener.onNestedChanged(mAppBarLayout, mVerticalOffset, mViewPager, mPositionOffset);
        }
    }
}
