package com.lge.ex16;

public class Button {
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    void click() {
        if (onClickListener != null) {
            onClickListener.onClick();
        }
    }
}
