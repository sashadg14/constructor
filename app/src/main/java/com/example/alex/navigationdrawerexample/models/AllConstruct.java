package com.example.alex.navigationdrawerexample.models;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.example.alex.navigationdrawerexample.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alex on 20.09.2017.
 */

public class AllConstruct implements Serializable{
    ArrayList<ViewConstructor> buttonsConstructs;

    public AllConstruct(){}
    public void addNewConstruct(ViewConstructor viewConstructor){
        if(buttonsConstructs==null)
        buttonsConstructs=new ArrayList<>();
        buttonsConstructs.add(viewConstructor);
    }

    public void createScreen(RelativeLayout layout, MainActivity activity){
        for(ViewConstructor viewConstructor: buttonsConstructs)
        layout.addView(viewConstructor.createElementInMainScreen(activity));
    }
    public void createCreatingScreen(RelativeLayout layout, Activity activity){
        for(ViewConstructor viewConstructor: buttonsConstructs)
        layout.addView(viewConstructor.createElementInCreatingScreen(activity));
    }

}
