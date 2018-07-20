package com.dyhdyh.helper.viewhelper;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ScrollView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengyuhan
 *         created 2018/7/20 16:02
 */
public class ViewHelper {

    public static void scrollToTop(final ScrollView sv) {
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.scrollTo(0, 0);
            }
        });
    }

    public static int getViewWidth(View v) {
        int viewWidth = v.getWidth();
        if (viewWidth > 0) {
            return viewWidth;
        }
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(width, height);
        return v.getMeasuredWidth();
    }

    public static int getViewHeight(View v) {
        int viewHeight = v.getHeight();
        if (viewHeight > 0) {
            return viewHeight;
        }
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(width, height);
        return v.getMeasuredHeight();
    }

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            AtomicInteger sNextGeneratedId = new AtomicInteger(1);
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }

    public static Bitmap getDrawingCache(View v) {
        v.setDrawingCacheEnabled(true);
        v.destroyDrawingCache();
        v.buildDrawingCache();
        return v.getDrawingCache();
    }

}
