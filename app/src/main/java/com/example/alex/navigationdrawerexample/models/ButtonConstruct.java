package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.Serializable;

/**
 * Created by alex on 20.09.2017.
 */

public class ButtonConstruct implements Serializable{
    private int height;
    private int width;
    private String text;
    private int paddingLeft;
    private int paddingTop;

    public ButtonConstruct() {
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public Button createButton(Activity activity){
        Button button=new Button(activity);
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(width, height);
        params.setMargins(paddingLeft,paddingTop,0,0);
        button.setText(text);
        button.setLayoutParams(params);
        return button;
    }
}
