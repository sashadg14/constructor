package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.content.pm.ProviderInfo;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.alex.navigationdrawerexample.R;

import java.io.Serializable;

/**
 * Created by alex on 20.09.2017.
 */

public class SeveralButtonsConstruct implements Serializable {
    private int height;
    private int width;
    private String[] text;
    private int paddingLeft;
    private int paddingTop;
    private int orientation;
    private int num;
    private int color=-65696;
    public SeveralButtonsConstruct(int num) {
        this.num=num;
        text=new String[num];
        for (int i=0;i<text.length;i++)
        text[i]=i+"";
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public View createElement(Activity activity){
        LinearLayout linearLayout = null;
        switch (num){
            case 1:
                linearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.button1,null);
                break;
            case 2:
                linearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.button2,null);
                break;
            case 3:
                linearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.button3,null);
                break;
            case 4:
                linearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.button4,null);
                break;
        }
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(width, height);
        params.setMargins(paddingLeft,paddingTop,0,0);
        for(int i=0;i<text.length;i++){
            ((Button)linearLayout.getChildAt(i)).setText(text[i]);
            linearLayout.getChildAt(i).setBackgroundDrawable(makeSelector(activity,color));
        }
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(orientation);
        return linearLayout;
    }

    public StateListDrawable makeSelector(Activity activity,int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        shape.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, activity.getResources().getDisplayMetrics()));

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setColor(color-10000);
        shape2.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, activity.getResources().getDisplayMetrics()));


        StateListDrawable res = new StateListDrawable();

        res.addState(new int[]{android.R.attr.state_pressed}, shape2);
        res.addState(new int[]{}, shape);
        return res;
    }
}
