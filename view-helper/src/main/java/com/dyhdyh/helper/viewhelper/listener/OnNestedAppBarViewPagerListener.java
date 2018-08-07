package com.dyhdyh.helper.viewhelper.listener;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;

/**
 * @author dengyuhan
 *         created 2018/8/7 16:24
 */
public interface OnNestedAppBarViewPagerListener {
    void onNestedChanged(AppBarLayout appBarLayout, int verticalOffset, ViewPager viewPager, float positionOffset);
}
