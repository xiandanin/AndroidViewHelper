package com.dyhdyh.helper.recyclerview.checkable;

/**
 * 单选辅助类
 *
 * @author dengyuhan
 *         created 2018/3/23 14:23
 */
public class SingleCheckableHelper implements CheckableHelper {

    private Integer mCheckedPosition;

    private SingleCheckableAdapter mCheckableAdapter;

    public SingleCheckableHelper(SingleCheckableAdapter checkableAdapter) {
        this.mCheckableAdapter = checkableAdapter;
    }

    /**
     * @param checkedPosition null代表清除
     */
    public void setCheckedPosition(Integer checkedPosition) {
        try {
            if (mCheckedPosition != null && mCheckedPosition >= 0) {
                mCheckableAdapter.onChecked(mCheckedPosition, false);
                mCheckableAdapter.onAdapterNotifyChanged(new int[]{mCheckedPosition});
            }
            this.mCheckedPosition = checkedPosition;
            mCheckableAdapter.onChecked(mCheckedPosition, true);
            mCheckableAdapter.onAdapterNotifyChanged(new int[]{mCheckedPosition});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getCheckedPosition() {
        return mCheckedPosition;
    }

    @Override
    public void clear() {
        if (mCheckedPosition != null && mCheckedPosition >= 0) {
            mCheckableAdapter.onChecked(mCheckedPosition, false);
            mCheckableAdapter.onAdapterNotifyChanged(new int[]{mCheckedPosition});
        }
        this.mCheckedPosition = null;
    }
}
