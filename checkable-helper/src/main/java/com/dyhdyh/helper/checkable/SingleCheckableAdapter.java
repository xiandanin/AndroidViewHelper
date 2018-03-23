package com.dyhdyh.helper.checkable;

/**
 * 单选
 * @author dengyuhan
 *         created 2018/3/23 14:27
 */
public interface SingleCheckableAdapter {

    void onChecked(int checkedPosition, boolean checked);

    void setCheckedPosition(int checkedPosition);

    int getCheckedPosition();
}
