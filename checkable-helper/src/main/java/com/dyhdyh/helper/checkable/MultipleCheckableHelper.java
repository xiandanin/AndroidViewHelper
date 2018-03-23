package com.dyhdyh.helper.checkable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:06
 */
public class MultipleCheckableHelper<Data> implements CheckableHelper {

    private List<Data> mCheckedData = new ArrayList<>();
    private List<Integer> mCheckedPositionArray = new ArrayList<>();
    private CheckableAdapter mCheckableAdapter;

    public MultipleCheckableHelper(CheckableAdapter checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    public void setCheckedPosition(int checkedPosition, boolean checked) {
        if (checked) {
            mCheckedPositionArray.add(checkedPosition);
        } else {
            mCheckedPositionArray.remove(Integer.valueOf(checkedPosition));
        }
        mCheckableAdapter.onChecked(CheckableAdapter.MODE_MULTIPLE, checkedPosition, checked);
    }

    public void setCheckedPositionArray(int[] checkedPositionArray, boolean checked) {
        for (int position : checkedPositionArray) {
            setCheckedPosition(position, checked);
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
