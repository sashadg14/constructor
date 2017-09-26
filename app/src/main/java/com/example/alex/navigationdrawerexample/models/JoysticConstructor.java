package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.alex.navigationdrawerexample.JoysticView;
import com.example.alex.navigationdrawerexample.MainActivity;
import com.example.alex.navigationdrawerexample.R;

import java.io.Serializable;

/**
 * Created by alex on 26.09.2017.
 */

public class JoysticConstructor implements ViewConstructor,Serializable{
    private int borderColour;
    private int buttonColour;
    private int height;
    private int width;
    private float Y;
    private float X;

    public int getBorderColour() {
        return borderColour;
    }

    public void setBorderColour(int borderColour) {
        this.borderColour = borderColour;
    }

    public int getButtonColour() {
        return buttonColour;
    }

    public void setButtonColour(int buttonColour) {
        this.buttonColour = buttonColour;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    @Override
    public View createElementInMainScreen(MainActivity activity) {
        JoysticView joysticView=new JoysticView(activity);
        joysticView.setX(X);
        joysticView.setY(Y);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        joysticView.setLayoutParams(params);
        joysticView.setEnabled(true);
        joysticView.setBorderColor(borderColour);
        joysticView.setButtonColor(buttonColour);
        return joysticView;
    }

    @Override
    public View createElementInCreatingScreen(Activity activity) {
        JoysticView joysticView=new JoysticView(activity);
        joysticView.setX(X);
        joysticView.setY(Y);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        joysticView.setLayoutParams(params);
        joysticView.setEnabled(false);
        joysticView.setBorderColor(borderColour);
        joysticView.setButtonColor(buttonColour);
        return joysticView;
    }
}
