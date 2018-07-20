package com.dyhdyh.helper.recyclerview.viewvisible;

import android.graphics.Rect;
import android.view.View;

/**
 * @author dengyuhan
 * created 2017/12/5 14:24
 */
public class ViewVisibleInfo {
    private View itemView;
    private int position;

    //true完全可见/部分可见;false不可见
    private boolean visible;

    //可见的坐标信息
    private Rect rect;

    //相对于自己的可见宽度百分比
    private float visibleWidthPercent;

    //相对于父View的可见宽度百分比
    private float visibleWidthPercentByParent;

    //相对于自己的可见高度百分比
    private float visibleHeightPercent;

    //相对于父View的可见高度百分比
    private float visibleHeightPercentByParent;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public float getVisibleWidthPercent() {
        return visibleWidthPercent;
    }

    public void setVisibleWidthPercent(float visibleWidthPercent) {
        this.visibleWidthPercent = visibleWidthPercent;
    }

    public float getVisibleWidthPercentByParent() {
        return visibleWidthPercentByParent;
    }

    public void setVisibleWidthPercentByParent(float visibleWidthPercentByParent) {
        this.visibleWidthPercentByParent = visibleWidthPercentByParent;
    }

    public float getVisibleHeightPercent() {
        return visibleHeightPercent;
    }

    public void setVisibleHeightPercent(float visibleHeightPercent) {
        this.visibleHeightPercent = visibleHeightPercent;
    }

    public float getVisibleHeightPercentByParent() {
        return visibleHeightPercentByParent;
    }

    public void setVisibleHeightPercentByParent(float visibleHeightPercentByParent) {
        this.visibleHeightPercentByParent = visibleHeightPercentByParent;
    }
}
