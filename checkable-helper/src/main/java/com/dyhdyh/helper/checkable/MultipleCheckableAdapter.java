package com.dyhdyh.helper.checkable;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:05
 */
public interface MultipleCheckableAdapter<Data> extends CheckableAdapter {

    Data getItem(int position);

    void setCheckedPositionArray(int[] checkedPositionArray, boolean checked);

    int[] getCheckedPositionArray();

    List<Data> getCheckedList();
}
