package com.dyhdyh.helper.checkable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:06
 */
public class MultipleCheckableHelper<Data> {

    private List<Data> mCheckedData = new ArrayList<>();
    private List<Integer> mCheckedPositionArray = new ArrayList<>();
    private MultipleCheckableAdapter<Data> mCheckableAdapter;

    public MultipleCheckableHelper(MultipleCheckableAdapter checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    public void setCheckedPositionArray(int[] checkedPositionArray, boolean checked) {
        for (int position : checkedPositionArray) {
            if (checked) {
                mCheckedPositionArray.add(Integer.valueOf(position));
            } else {
                mCheckedPositionArray.remove(Integer.valueOf(position));
            }
            mCheckableAdapter.onChecked(mCheckedData, position, checked);
        }
    }


    public List<Integer> getCheckedPositionList() {
        return mCheckedPositionArray;
    }


    public List<Data> getCheckedData() {
        return mCheckedData;
    }

    public void clear() {
        mCheckedData.clear();
    }

}
