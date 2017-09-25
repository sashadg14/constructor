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
    ArrayList<SeveralButtonsConstruct> buttonsConstructs;

    public AllConstruct() {
        ArrayList<SeveralButtonsConstruct> arrayList= new ArrayList<>();
    }
    public void addNewConstruct(SeveralButtonsConstruct buttonsConstruct){
        if(buttonsConstructs==null)
        buttonsConstructs=new ArrayList<>();
        buttonsConstructs.add(buttonsConstruct);

    }
    public void createScreen(RelativeLayout layout, Activity activity){
        for(SeveralButtonsConstruct buttonsConstruct: buttonsConstructs)
        layout.addView(buttonsConstruct.createElement(activity));
    }
}
