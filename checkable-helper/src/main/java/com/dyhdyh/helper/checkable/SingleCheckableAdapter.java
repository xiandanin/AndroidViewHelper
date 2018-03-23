package com.dyhdyh.helper.checkable;

/**
 * 单选
 * @author dengyuhan
 *         created 2018/3/23 14:27
 */
public interface SingleCheckableAdapter extends CheckableAdapter{


    void setCheckedPosition(Integer checkedPosition);

    Integer getCheckedPosition();
}
