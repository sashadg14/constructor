package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alex on 21.09.2017.
 */

public class JoysticChanger {
    RelativeLayout layout;
    Activity activity;
    TextView textView;
    int colour;
    public JoysticChanger(RelativeLayout layout, Activity activity) {
        this.layout=layout;
        textView=(TextView)activity.findViewById(R.id.textView);
        this.activity = activity;
    }
    public void clickPositionChanging(final View newView){

        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(newView.getWidth(), newView.getHeight());
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.setMargins((int) event.getX()- newView.getWidth()/2,(int) event.getY()- newView.getHeight()/2,0,0);
                newView.setLayoutParams(params);
                /*MainActivity.this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,
                        (int) event.getX()-newView.getWidth()/2,(int) event.getY()-newView.getHeight()/2,0));*/
                return true;
            }
        });
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.setMargins((int)( event.getX()- newView.getWidth()/2+ newView.getX()),(int)( event.getY()- newView.getHeight()/2+ newView.getY()),0,0);
                newView.setLayoutParams(params);
                return false;
            }
        });

        textView.setText("Перетащите элемент");
    }
}
