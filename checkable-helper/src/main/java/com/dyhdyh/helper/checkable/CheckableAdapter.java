package com.dyhdyh.helper.checkable;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:39
 */
public interface CheckableAdapter {
    int MODE_SINGLE = 1;
    int MODE_MULTIPLE = 2;

    void onChecked(int mode, int checkedPosition, boolean checked);

    void clear();
}
