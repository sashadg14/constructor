package com.example.alex.navigationdrawerexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.alex.navigationdrawerexample.models.AllConstruct;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class MainActivity extends BluetoozHandler {
    private String[] listTitles;
    private ListView mDrawerListView;
    RelativeLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout =(RelativeLayout) findViewById(R.id.rel_screen);

        listTitles = getResources().getStringArray(R.array.comands);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        //createElementInMainScreen();
        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, listTitles));
        createScreen();
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: startActivity(new Intent(MainActivity.this,ElementsPickActivity.class));
                        break;
                    case 1:
                        /*try {
                            SharedPreferences sPref = getSharedPreferences("pref",MODE_PRIVATE);
                            String savedText = sPref.getString("button", "");
                        byte b[] = Base64.decode(savedText.getBytes(),0);
                        ByteArrayInputStream bi = new ByteArrayInputStream(b);
                        ObjectInputStream si = new ObjectInputStream(bi);
                            AllConstruct construct=(AllConstruct) si.readObject();
                        //ButtonConstruct buttonConstruct = (ButtonConstruct) si.readObject();
                          //  layout.addView(buttonConstruct.createElementInMainScreen(MainActivity.this));

                            construct.createScreen(layout,MainActivity.this);

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }*/

                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
    }
    private void createScreen(){
        try {
            SharedPreferences sPref = getSharedPreferences("pref",MODE_PRIVATE);
            String savedText = sPref.getString("button", "");
            byte b[] = Base64.decode(savedText.getBytes(),0);
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            AllConstruct construct=(AllConstruct) si.readObject();
            //ButtonConstruct buttonConstruct = (ButtonConstruct) si.readObject();
            //  layout.addView(buttonConstruct.createElementInMainScreen(MainActivity.this));

            construct.createScreen(layout,MainActivity.this);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}