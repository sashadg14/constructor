package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.navigationdrawerexample.color_picker.AmbilWarnaDialogFragment;
import com.example.alex.navigationdrawerexample.color_picker.OnAmbilWarnaListener;
import com.example.alex.navigationdrawerexample.models.SeveralButtonsConstruct;

/**
 * Created by alex on 18.09.2017.
 */

public class ElementsCreator {
    RelativeLayout layout;
   // View newView;
    Activity activity;
    TextView textView;
    int colour=-65696;
    public ElementsCreator(RelativeLayout layout, Activity activity) {
        this.layout = layout;
        //this.newView = newView;
        textView=(TextView)activity.findViewById(R.id.textView);
        this.activity = activity;
    }

    public View createButtons(int col){
        View newView = null;
        switch (col){
            case 1:
                newView=activity.getLayoutInflater().inflate(R.layout.button1,null);
                break;
            case 2:
                newView=activity.getLayoutInflater().inflate(R.layout.button2,null);
                break;
            case 3:
                newView=activity.getLayoutInflater().inflate(R.layout.button3,null);
                break;
            case 4:
                newView=activity.getLayoutInflater().inflate(R.layout.button4,null);
                break;
        }
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 100);
        //button.setBackgroundDrawable(makeSelector(dataBaseReader.getColourButtons()));
        newView.setLayoutParams(params);
        for (int i=0;i<((LinearLayout)newView).getChildCount();i++){
            ((LinearLayout)newView).getChildAt(i).setBackgroundDrawable(makeSelector(colour));
            ((LinearLayout)newView).getChildAt(i).setClickable(false);

        }
        layout.addView(newView,layout.getChildCount()-1);
        newView.setBackgroundResource(R.drawable.red_shape);
        return newView;
    }

    public View createJoy(){
        View newView =activity.getLayoutInflater().inflate(R.layout.joystic,null);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(400, 400);
        //button.setBackgroundDrawable(makeSelector(dataBaseReader.getColourButtons()));
        newView.setLayoutParams(params);

        layout.addView(newView);
        return newView;
    }


    public void clickPositionChanging(final View newView){

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
        try {
            final LinearLayout linearLayout=(LinearLayout)newView;
            for (int i=0;i<linearLayout.getChildCount();i++){
                final int finalI = i;
                linearLayout.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        params.setMargins((int)( event.getX()- linearLayout.getChildAt(finalI).getWidth()/2+ newView.getX()),(int)( event.getY()- linearLayout.getChildAt(finalI).getHeight()/2+ newView.getY()),0,0);
                        newView.setLayoutParams(params);
                        return false;
                    }
                });
            }
        }
        catch (Exception e){
            newView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    params.setMargins((int)( event.getX()- finalNewView1.getWidth()/2+ finalNewView1.getX()),(int)( event.getY()- finalNewView1.getHeight()/2+ finalNewView1.getY()),0,0);
                    finalNewView1.setLayoutParams(params);
                    return false;
                }
            });
        }

          textView.setText("Перетащите элемент");
    }
    public void setNullTochListeners(View newView){
        layout.setOnTouchListener(null);
        newView.setOnTouchListener(null);
        try {
            final LinearLayout linearLayout=(LinearLayout)newView;
            for (int i=0;i<linearLayout.getChildCount();i++){
                final int finalI = i;
                linearLayout.getChildAt(i).setOnTouchListener(null);
            }
        }
        catch (Exception e){
        }
    }
    public void proportionsChanging(final View newView){
        final View finalNewView = newView;
        setNullTochListeners(newView);
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

    public void createTextEnteringDialog(final View newView, final int count, final int totalIndex) {
/* Alert Dialog Code Start*/
        setNullTochListeners(newView);
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Введите название"); //Set Alert dialog title here
        // Set an EditText view to get user input
        final EditText input = new EditText(activity);
        input.setText(((Button)((LinearLayout)newView).getChildAt(totalIndex)).getText().toString());
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //You will get as string input data in this variable.
                // here we convert the input to a string and show in a toast.
                ((Button)((LinearLayout)newView).getChildAt(totalIndex)).setText(input.getText().toString());
                if(count>1&&count<=4&&totalIndex+1<count){
                    dialog.cancel();
                    createTextEnteringDialog(newView,count,totalIndex+1);
                }
                ((TextView)activity.findViewById(R.id.textView)).setText("Нажмите \"назад\" чтобы сохранить");
                //Toast.makeText(MainActivity.this,srt,Toast.LENGTH_LONG).show();
            } // End of onClick(DialogInterface dialog, int whichButton)
        }); //End of alert.setPositiveButton
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ((TextView)activity.findViewById(R.id.textView)).setText("Нажмите \"назад\" чтобы сохранить");
                dialog.cancel();
            }
        }); //End of alert.setNegativeButton
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        ((TextView)activity.findViewById(R.id.textView)).setText("Изменение текста");
       /* Alert Dialog Code End*/
        //Toast.makeText(getApplication(),"Введите название",Toast.LENGTH_LONG).show();
    }

    public void chooseOrintationDialog(final View newView){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.orientation_choose_dialog);

        builder.setItems(R.array.orientations, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: ((LinearLayout)newView).setOrientation(LinearLayout.VERTICAL);

                        break;
                    case 1: ((LinearLayout)newView).setOrientation(LinearLayout.HORIZONTAL);
                        break;
                }
                RelativeLayout.LayoutParams par=(RelativeLayout.LayoutParams)newView.getLayoutParams();
                par.width=newView.getHeight();
                par.height=newView.getWidth();
            }
        });

        builder.create().show();
    }

    public void chooseColorDialog(final View newView){
        final int colour=-65696;
        System.out.println("fsdakjhhfdslkdasfnkdshlfkasdfnkashnfaksd");
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
                        System.out.println(color);
                        ElementsCreator.this.colour=color;
                        for (int i=0;i<((LinearLayout)newView).getChildCount();i++){
                            ((LinearLayout)newView).getChildAt(i).setBackgroundDrawable(makeSelector(color));

                        }

                    }
                };

                // create new instance of AmbilWarnaDialogFragment and set OnAmbilWarnaListener listener to it
                // show dialog fragment with some tag value
                FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
                AmbilWarnaDialogFragment fragment = AmbilWarnaDialogFragment.newInstance(colour);
                fragment.setOnAmbilWarnaListener(onAmbilWarnaListener);
                System.out.println("fsdakjhhfdslkdasfnkdshlfkasdfnkashnfaksd");
                fragment.show(ft, "color_picker_dialog");
            }
        },10);
    }
    private StateListDrawable makeSelector(int color) {
        final GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        shape.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, activity.getResources().getDisplayMetrics()));

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shape});

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setColor(color-10000);
        shape2.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, activity.getResources().getDisplayMetrics()));


        StateListDrawable res = new StateListDrawable();

        res.addState(new int[]{android.R.attr.state_pressed}, shape2);
        res.addState(new int[]{}, layerDrawable);
        return res;
    }

    public int getColour() {
        return colour;
    }
}
