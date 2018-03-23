package com.dyhdyh.helper.checkable;

import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:05
 */
public interface MultipleCheckableAdapter<Data> {

    void onChecked(List<Data> checkedData, int checkedPosition, boolean checked);

    void setCheckedPositionArray(int[] checkedPositionArray, boolean checked);

    List<Integer> getCheckedPositionList();

    List<Data> getCheckedData();
}
