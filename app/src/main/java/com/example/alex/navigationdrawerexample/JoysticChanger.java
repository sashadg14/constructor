package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex.navigationdrawerexample.color_picker.AmbilWarnaDialogFragment;
import com.example.alex.navigationdrawerexample.color_picker.OnAmbilWarnaListener;

/**
 * Created by alex on 21.09.2017.
 */

public class JoysticChanger {
    RelativeLayout layout;
    Activity activity;
    TextView textView;
    public JoysticChanger(RelativeLayout layout, Activity activity) {
        this.layout=layout;
        textView=(TextView)activity.findViewById(R.id.textView);
        this.activity = activity;
    }
    public View createJoy(){
        View newView =activity.getLayoutInflater().inflate(R.layout.joystic,null);
        newView.setBackgroundResource(R.drawable.red_shape);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(400, 400);
        //button.setBackgroundDrawable(makeSelector(dataBaseReader.getColourButtons()));
        newView.setLayoutParams(params);

        layout.addView(newView);
        return newView;
    }
    public void clickPositionChanging(final View newView){

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newView.setX(event.getX()- newView.getWidth()/2);
                newView.setY(event.getY()- newView.getHeight()/2);
                /*MainActivity.this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,
                        (int) event.getX()-newView.getWidth()/2,(int) event.getY()-newView.getHeight()/2,0));*/
                return true;
            }
        });
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newView.setX(event.getX()- newView.getWidth()/2+ newView.getX());
                newView.setY(event.getY()- newView.getHeight()/2+ newView.getY());
                return false;
            }
        });

        textView.setText("Перетащите элемент");
    }
    public void proportionsChanging(final View newView){
        final View finalNewView = newView;
        //setNullTochListeners(newView);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(newView.getWidth(), newView.getHeight());

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    params.width = (int) (event.getX() - newView.getX());
                    params.height = params.width;
                    newView.setLayoutParams(params);
                /*MainActivity.this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,
                        (int) event.getX()-newView.getWidth()/2,(int) event.getY()-newView.getHeight()/2,0));*/
                return true;
            }
        });
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                params.width= (int) (event.getX()- newView.getX());
                params.height= (int) (event.getY()- newView.getY());
                newView.setLayoutParams(params);
                return false;
            }
        });
        textView.setText("Потяните чтобы изменить размер");
    }
    public void chooseBorderColorDialog(final View newView){
        final int colour=-65696;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                OnAmbilWarnaListener onAmbilWarnaListener = new OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialogFragment dialogFragment) {
                        Log.d("TAG", "onCancel()");
                    }

                    @Override
                    public void onOk(AmbilWarnaDialogFragment dialogFragment,int color) {
                        ((JoysticView)newView).setBorderColor(color);
                    }
                };

                // create new instance of AmbilWarnaDialogFragment and set OnAmbilWarnaListener listener to it
                // show dialog fragment with some tag value
                FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
                AmbilWarnaDialogFragment fragment = AmbilWarnaDialogFragment.newInstance(colour);
                fragment.setOnAmbilWarnaListener(onAmbilWarnaListener);
                fragment.show(ft, "color_picker_dialog");
            }
        },10);
    }

    public void chooseButtonColorDialog(final View newView){
        final int colour=-65696;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                OnAmbilWarnaListener onAmbilWarnaListener = new OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialogFragment dialogFragment) {
                        Log.d("TAG", "onCancel()");
                    }

                    @Override
                    public void onOk(AmbilWarnaDialogFragment dialogFragment,int color) {
                        ((JoysticView)newView).setButtonColor(color);
                    }
                };

                // create new instance of AmbilWarnaDialogFragment and set OnAmbilWarnaListener listener to it
                // show dialog fragment with some tag value
                FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
                AmbilWarnaDialogFragment fragment = AmbilWarnaDialogFragment.newInstance(colour);
                fragment.setOnAmbilWarnaListener(onAmbilWarnaListener);
                fragment.show(ft, "color_picker_dialog");
            }
        },10);
    }
}
