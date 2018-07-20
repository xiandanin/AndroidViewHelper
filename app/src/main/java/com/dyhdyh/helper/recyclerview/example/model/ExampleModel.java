package com.dyhdyh.helper.recyclerview.example.model;

/**
 * @author dengyuhan
 *         created 2018/3/23 14:58
 */
public class ExampleModel {
    private String label;
    private boolean checked;

    public ExampleModel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
