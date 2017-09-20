package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex.navigationdrawerexample.models.ButtonConstruct;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ElementCreatingActivity extends AppCompatActivity {
    private String[] listTitles;
    private ListView mDrawerListView;
    private View newView;
    RelativeLayout relativeLayoutToolbar;
    RelativeLayout layout;
    private String element="";
    private final String elButton1="button1",elButton2="button2",elButton3="button3",elButton4="button4",elJoy="joy",elBtnJoy="btn_joy";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_creating);
        relativeLayoutToolbar =(RelativeLayout)findViewById(R.id.rel_tollbar);
        layout =(RelativeLayout) findViewById(R.id.rel_screen);

        element=getIntent().getStringExtra("element");

        createElement();
        // подключим адаптер для списка


    }
    private void createDrawer(){
        listTitles = getResources().getStringArray(R.array.comands_redacting);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: new ElementsCreator(layout,ElementCreatingActivity.this).proportionsChanging(newView);
                        break;
                    case 1: new ElementsCreator(layout,ElementCreatingActivity.this).clickPositionChanging(newView);
                        break;
                    case 2: new ElementsCreator(layout,ElementCreatingActivity.this).createTextEnteringDialog(newView);
                        break;
                    /*case 1:
                    case 1:
                    case 1:*/
                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, listTitles));
    }

    private void createDrawer2(){
        listTitles = getResources().getStringArray(R.array.comands_redacting2);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: new ElementsCreator(layout,ElementCreatingActivity.this).chooseOrintationDialog(newView);
                        break;
                    case 1: new ElementsCreator(layout,ElementCreatingActivity.this).proportionsChanging(newView);
                        break;
                    case 2: new ElementsCreator(layout,ElementCreatingActivity.this).clickPositionChanging(newView);
                        break;
                    case 3: new ElementsCreator(layout,ElementCreatingActivity.this).createTextEnteringDialog(newView);
                        break;
                    /*case 1:
                    case 1:
                    case 1:*/
                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, listTitles));
    }

    private void createElement(){
        if(element==null||element.equalsIgnoreCase(""))
            return;
        //if(element.equalsIgnoreCase(elButton1))
        /*(toolbar.findViewById(R.id.txtButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ElementsCreator(layout,ElementCreatingActivity.this).createTextEnteringDialog(newView);
            }
        });

        (toolbar.findViewById(R.id.positionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ElementsCreator(layout,ElementCreatingActivity.this).clickPositionChanging(newView);
            }
        });

        (toolbar.findViewById(R.id.sizeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ElementsCreator(layout,ElementCreatingActivity.this).proportionsChanging(newView);
                //Toast.makeText(getApplication(),"fdsfadsasdf",Toast.LENGTH_LONG).show();
            }
        });*/
        switch (element){

            case elButton1:
                newView= new ElementsCreator(layout,ElementCreatingActivity.this).createButton();
                createDrawer();
                break;
            case elButton2:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).create2Buttons();
                createDrawer2();
                break;
            case elButton3:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).create3Buttons();
                createDrawer2();
                break;
            case elButton4:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).create4Buttons();
                createDrawer2();
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            layout.setOnTouchListener(null);
            newView.setOnTouchListener(null);
            if( !((TextView)findViewById(R.id.textView)).getText().toString().equalsIgnoreCase("Нажмите \"назад\" чтобы сохранить")) {
                ((TextView)findViewById(R.id.textView)).setText("Нажмите \"назад\" чтобы сохранить");
                return false;
            }else {
                saveInFile();
                startActivity(new Intent(ElementCreatingActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveInFile(){
        String redisString = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(btnConstr());
            so.flush();
            redisString = new String(Base64.encode(bo.toByteArray(),0));
        }
        catch (Exception e) {
            e.printStackTrace();        }


        SharedPreferences sPref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
                ed.putString("button", redisString);
                /*ed.putString("email", "");
                ed.putString("userName", "");*/
        ed.commit();
    }
    public ButtonConstruct btnConstr(){
        ButtonConstruct buttonConstruct= new ButtonConstruct();
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)newView.getLayoutParams();
        buttonConstruct.setHeight(params.height);
        buttonConstruct.setWidth(params.width);
        buttonConstruct.setPaddingLeft(params.leftMargin);
        buttonConstruct.setPaddingTop(params.topMargin);
        buttonConstruct.setText(((Button)newView).getText().toString());
        return buttonConstruct;
    }
}
