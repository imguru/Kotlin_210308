package com.lge.ex16;

public class Button {


    private OnClickListener onClickListener;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    void click() {
        if (onClickListener != null) {
            onClickListener.onClick();
        }
    }
}
