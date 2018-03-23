package com.dyhdyh.helper.checkable;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:27
 */
public interface CheckableAdapter {

    void onChecked(int checkedPosition, boolean checked);

    void setCheckedPosition(int checkedPosition);

    int getCheckedPosition();
}
