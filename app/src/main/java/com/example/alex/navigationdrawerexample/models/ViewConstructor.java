package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.alex.navigationdrawerexample.MainActivity;
import com.example.alex.navigationdrawerexample.R;

/**
 * Created by alex on 26.09.2017.
 */

public interface ViewConstructor{
    public View createElementInMainScreen(final MainActivity activity);
    public View createElementInCreatingScreen(final Activity activity);

}
