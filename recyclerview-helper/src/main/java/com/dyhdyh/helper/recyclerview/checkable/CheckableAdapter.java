package com.dyhdyh.helper.recyclerview.checkable;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:39
 */
public interface CheckableAdapter {

    void onAdapterNotifyChanged(int[] checkedPositionArray);

    void onChecked(int checkedPosition, boolean checked);

    void clear();
}
