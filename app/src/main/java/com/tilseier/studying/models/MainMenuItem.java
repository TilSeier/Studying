package com.tilseier.studying.models;

import android.view.View;

public class MainMenuItem {
    String text;
    String onClickMethodName;
    View.OnClickListener onClickMethod;

    public MainMenuItem(String text, View.OnClickListener onClickMethod, String onClickMethodName) {
        this.text = text;
        this.onClickMethodName = onClickMethodName;
        this.onClickMethod = onClickMethod;
    }

    public String getText() {
        return text;
    }

    public View.OnClickListener getOnClickMethod() {
        return onClickMethod;
    }

    public String getOnClickMethodName() {
        return onClickMethodName;
    }

}
