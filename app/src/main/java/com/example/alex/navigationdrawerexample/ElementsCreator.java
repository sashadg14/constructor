package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alex on 18.09.2017.
 */

public class ElementsCreator {
    RelativeLayout layout;
   // View newView;
    Activity activity;
    TextView textView;
    public ElementsCreator(RelativeLayout layout, View newView, Activity activity) {
        this.layout = layout;
        //this.newView = newView;
        textView=(TextView)activity.findViewById(R.id.textView);
        this.activity = activity;
    }

    public Button createButton(){
        Button newView=new Button(activity);
        ((Button)newView).setText("btn");
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        //button.setBackgroundDrawable(makeSelector(dataBaseReader.getColourButtons()));
        newView.setLayoutParams(params);
        layout.addView(newView);

        return newView;
    }

    public void clickPositionChanging(View newView){

        final View finalNewView = newView;
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(newView.getWidth(), newView.getHeight());
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.setMargins((int) event.getX()- finalNewView.getWidth()/2,(int) event.getY()- finalNewView.getHeight()/2,0,0);
                finalNewView.setLayoutParams(params);
                /*MainActivity.this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,
                        (int) event.getX()-newView.getWidth()/2,(int) event.getY()-newView.getHeight()/2,0));*/
                return true;
            }
        });
        final View finalNewView1 = newView;
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.setMargins((int)( event.getX()- finalNewView1.getWidth()/2+ finalNewView1.getX()),(int)( event.getY()- finalNewView1.getHeight()/2+ finalNewView1.getY()),0,0);
                finalNewView1.setLayoutParams(params);
                return false;
            }
        });
          textView.setText("Перетащите элемент");
    }
    public void proportionsChanging(final View newView){
        final View finalNewView = newView;
        layout.setOnTouchListener(null);
        newView.setOnTouchListener(null);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(newView.getHeight(), newView.getWidth());
        params.setMargins((int) newView.getX(),(int)newView.getY(),0,0);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.width= (int) (event.getX()- newView.getX());
                params.height= (int) (event.getY()- newView.getY());
                finalNewView.setLayoutParams(params);
                /*MainActivity.this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,
                        (int) event.getX()-newView.getWidth()/2,(int) event.getY()-newView.getHeight()/2,0));*/
                return true;
            }
        });
        final View finalNewView1 = newView;
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.width= (int) (event.getX()- newView.getX());
                params.height= (int) (event.getY()- newView.getY());
                finalNewView.setLayoutParams(params);
                return false;
            }
        });
        textView.setText("Потяните чтобы изменить размер");
    }
}
