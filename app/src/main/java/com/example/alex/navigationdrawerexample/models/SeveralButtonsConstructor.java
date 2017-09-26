package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.alex.navigationdrawerexample.ElementCreatingActivity;
import com.example.alex.navigationdrawerexample.MainActivity;
import com.example.alex.navigationdrawerexample.R;

import java.io.Serializable;

/**
 * Created by alex on 20.09.2017.
 */

public class SeveralButtonsConstructor implements Serializable,ViewConstructor {
    private int height;
    private int width;
    private String[] text;
    private float X;
    private float Y;
    private int orientation;
    private int num;
    private String[] comand;
    private int color=-65696;
    public SeveralButtonsConstructor(int num) {
        this.num=num;
        text=new String[num];
        for (int i=0;i<text.length;i++)
        text[i]=i+"";
    }

    public String[] getComand() {
        return comand;
    }

    public void setComand(String[] comand) {
        this.comand = comand;
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

    public void setX(float x) {
        this.X = x;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setY(float y) {
        this.Y = y;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public View createElementInMainScreen(final MainActivity activity){
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
        Button button;
        for(int i=0;i<text.length;i++){
            button=((Button)linearLayout.getChildAt(i));
            button.setText(text[i]);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.sendData(comand[finalI]);
                }
            });
            linearLayout.getChildAt(i).setBackgroundDrawable(makeSelector(activity,color));

        }
        linearLayout.setX(X);
        linearLayout.setY(Y);
        linearLayout.setOrientation(orientation);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        linearLayout.setLayoutParams(params);

        return linearLayout;
    }
    public View createElementInCreatingScreen(final Activity activity){
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
        Button button;
        for(int i=0;i<text.length;i++){
            button=((Button)linearLayout.getChildAt(i));
            button.setText(text[i]);
            button.setOnClickListener(null);
            linearLayout.getChildAt(i).setBackgroundDrawable(makeSelector(activity,color));

        }
        linearLayout.setX(X);
        linearLayout.setY(Y);
        linearLayout.setOrientation(orientation);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        linearLayout.setLayoutParams(params);

        return linearLayout;
    }

    private StateListDrawable makeSelector(Activity activity,int color) {
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
