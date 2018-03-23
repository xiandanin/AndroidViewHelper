package com.dyhdyh.helper.checkable;

/**
 * 单选辅助类
 *
 * @author dengyuhan
 *         created 2018/3/23 14:23
 */
public class SingleCheckableHelper implements CheckableHelper {

    private Integer mCheckedPosition;

    private CheckableAdapter mCheckableAdapter;

    public SingleCheckableHelper(CheckableAdapter checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    /**
     * @param checkedPosition null代表清除
     */
    public void setCheckedPosition(Integer checkedPosition) {
        try {
            if (mCheckedPosition != null && mCheckedPosition >= 0) {
                mCheckableAdapter.onChecked(CheckableAdapter.MODE_SINGLE, mCheckedPosition, false);
            }
            this.mCheckedPosition = checkedPosition;
            mCheckableAdapter.onChecked(CheckableAdapter.MODE_SINGLE, mCheckedPosition, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getCheckedPosition() {
        return mCheckedPosition;
    }

    @Override
    public void clear() {
        if (mCheckedPosition >= 0) {
            mCheckableAdapter.onChecked(CheckableAdapter.MODE_SINGLE, mCheckedPosition, false);
        }
        this.mCheckedPosition = null;
    }
}
