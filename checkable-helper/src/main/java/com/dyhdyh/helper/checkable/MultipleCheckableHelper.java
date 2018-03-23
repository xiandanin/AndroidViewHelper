package com.dyhdyh.helper.checkable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dengyuhan
 *         created 2018/3/23 15:06
 */
public class MultipleCheckableHelper<Data> implements CheckableHelper {

    private Map<Integer, Data> mCheckedData = new LinkedHashMap<>();
    private MultipleCheckableAdapter<Data> mCheckableAdapter;

    public MultipleCheckableHelper(MultipleCheckableAdapter<Data> checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    protected void setCheckedPosition(int checkedPosition, boolean checked) {
        mCheckableAdapter.onChecked(checkedPosition, checked);
        Data data = mCheckableAdapter.getItem(checkedPosition);
        if (checked) {
            mCheckedData.put(checkedPosition, data);
        } else {
            mCheckedData.remove(checkedPosition);
        }
    }

    public void setCheckedPositionArray(int[] checkedPositionArray, boolean checked) {
        for (int position : checkedPositionArray) {
            setCheckedPosition(position, checked);
        }
        mCheckableAdapter.onAdapterNotifyChanged(checkedPositionArray);
    }


    public int[] getCheckedPositionArray() {
        int[] positionArray = new int[mCheckedData.size()];
        Set<Map.Entry<Integer, Data>> entries = mCheckedData.entrySet();
        int index = 0;
        for (Map.Entry<Integer, Data> entry : entries) {
            positionArray[index] = entry.getKey();
            index++;
        }
        return positionArray;
    }


    public List<Data> getCheckedList() {
        List<Data> checkedList = new ArrayList<>();
        Set<Map.Entry<Integer, Data>> entries = mCheckedData.entrySet();
        for (Map.Entry<Integer, Data> entry : entries) {
            checkedList.add(entry.getValue());
        }
        return checkedList;
    }

    public Map<Integer, Data> getCheckedData() {
        return mCheckedData;
    }

    public void clear() {
        Set<Map.Entry<Integer, Data>> entries = mCheckedData.entrySet();
        for (Map.Entry<Integer, Data> entry : entries) {
            mCheckableAdapter.onChecked(entry.getKey(), false);
        }
        mCheckedData.clear();
        mCheckableAdapter.onAdapterNotifyChanged(new int[0]);
    }

}
