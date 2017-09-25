package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex.navigationdrawerexample.models.AllConstruct;
import com.example.alex.navigationdrawerexample.models.SeveralButtonsConstruct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ElementCreatingActivity extends AppCompatActivity{
    private String[] listTitles;
    private ListView mDrawerListView;
    private View newView;
    SeveralButtonsConstruct severalButtonsConstruct;
    private ElementsCreator elementsCreator;
    RelativeLayout relativeLayoutToolbar;
    RelativeLayout layout;
    private String element="";
    AllConstruct construct;
    private final String elButton1="button1",elButton2="button2",elButton3="button3",elButton4="button4",elJoy="joy",elBtnJoy="btn_joy";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_creating);

        relativeLayoutToolbar =(RelativeLayout)findViewById(R.id.rel_tollbar);
        layout =(RelativeLayout) findViewById(R.id.rel_screen);

        elementsCreator=new ElementsCreator(layout,this);
        element=this.getIntent().getStringExtra("element");

        createElement();
        // подключим адаптер для списка

        try {
            SharedPreferences sPref = getSharedPreferences("pref",MODE_PRIVATE);
            String savedText = sPref.getString("button", "");
            byte b[] = Base64.decode(savedText.getBytes(),0);
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            construct=(AllConstruct) si.readObject();
            //ButtonConstruct buttonConstruct = (ButtonConstruct) si.readObject();
            //  layout.addView(buttonConstruct.createElement(MainActivity.this));

            construct.createScreen(layout,ElementCreatingActivity.this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ((TextView)findViewById(R.id.textView)).setText("Потяните вправо чтобы открыть меню");
    }

    private void createDrawer(){
        listTitles = getResources().getStringArray(R.array.comands_redacting);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:elementsCreator.proportionsChanging(newView);
                        break;
                    case 1: elementsCreator.clickPositionChanging(newView);
                        break;
                    case 2: elementsCreator.createTextEnteringDialog(newView);
                        break;
                    case 3: elementsCreator.chooseColorDialog(newView);
                        break;
                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, listTitles));
    }
    private void createDrawerForJoy(){
        listTitles = getResources().getStringArray(R.array.comands_joy);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:elementsCreator.proportionsChanging(newView);
                        break;
                    case 1: new JoysticChanger(layout,ElementCreatingActivity.this).clickPositionChanging(newView);
                        break;
                    case 2: elementsCreator.createTextEnteringDialog(newView);
                        break;
                    case 3: elementsCreator.chooseColorDialog(newView);
                        break;
                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, listTitles));
    }

    private void createDrawer2(){
        listTitles = getResources().getStringArray(R.array.comands_redacting2);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: elementsCreator.chooseOrintationDialog(newView);
                        break;
                    case 1: elementsCreator.proportionsChanging(newView);
                        break;
                    case 2:elementsCreator.clickPositionChanging(newView);
                        break;
                    case 3: elementsCreator.createTextEnteringDialog(newView);
                        break;
                    case 4: elementsCreator.chooseColorDialog(newView);
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
                newView= new ElementsCreator(layout,ElementCreatingActivity.this).createButtons(1);
                severalButtonsConstruct=new SeveralButtonsConstruct(1);
                createDrawer();
                break;
            case elButton2:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).createButtons(2);
                severalButtonsConstruct=new SeveralButtonsConstruct(2);
                createDrawer2();
                break;
            case elButton3:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).createButtons(3);
                severalButtonsConstruct=new SeveralButtonsConstruct(3);
                createDrawer2();
                break;
            case elButton4:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).createButtons(4);
                severalButtonsConstruct=new SeveralButtonsConstruct(4);
                createDrawer2();
                break;
            case elJoy:
                 newView= new ElementsCreator(layout,ElementCreatingActivity.this).createJoy();
                 newView.setClickable(false);
                createDrawerForJoy();
                //severalButtonsConstruct=new SeveralButtonsConstruct(4);
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
                elementsCreator.setNullTochListeners(newView);
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

        if(construct==null)
            construct=new AllConstruct();
        construct.addNewConstruct(sevBtnConstr());

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject((construct));
            so.flush();
            redisString = new String(Base64.encode(bo.toByteArray(),0));
        }

        catch (Exception e) {
            e.printStackTrace();        }

        SharedPreferences sPref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
                ed.putString("button", redisString);

        ed.commit();
    }

    public SeveralButtonsConstruct sevBtnConstr(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)newView.getLayoutParams();
        severalButtonsConstruct.setHeight(params.height);
        severalButtonsConstruct.setWidth(params.width);
        severalButtonsConstruct.setPaddingLeft(params.leftMargin);
        severalButtonsConstruct.setPaddingTop(params.topMargin);
        severalButtonsConstruct.setOrientation(((LinearLayout)newView).getOrientation());
        String[] strings=new String[((LinearLayout) newView).getChildCount()];
        for(int i=0;i<((LinearLayout) newView).getChildCount();i++)
            strings[i]=((Button)((LinearLayout) newView).getChildAt(i)).getText().toString();
            severalButtonsConstruct.setText(strings);
        severalButtonsConstruct.setColor(elementsCreator.getColour());
        return severalButtonsConstruct;
    }
}
