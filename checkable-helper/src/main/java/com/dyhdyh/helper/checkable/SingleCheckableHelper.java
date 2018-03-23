package com.dyhdyh.helper.checkable;

/**
 * 单选辅助类
 *
 * @author dengyuhan
 *         created 2018/3/23 14:23
 */
public class SingleCheckableHelper {
    private int mCheckedPosition = -1;

    private CheckableAdapter mCheckableAdapter;

    public SingleCheckableHelper(CheckableAdapter checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    public void setCheckedPosition(int checkedPosition) {
        if (mCheckedPosition >= 0) {
            mCheckableAdapter.onChecked(mCheckedPosition, false);
        }
        this.mCheckedPosition = checkedPosition;
        mCheckableAdapter.onChecked(mCheckedPosition, true);
    }

    public int getCheckedPosition() {
        return mCheckedPosition;
    }

}
