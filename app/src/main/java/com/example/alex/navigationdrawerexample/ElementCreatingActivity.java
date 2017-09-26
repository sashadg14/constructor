package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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
import com.example.alex.navigationdrawerexample.models.JoysticConstructor;
import com.example.alex.navigationdrawerexample.models.SeveralButtonsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ElementCreatingActivity extends AppCompatActivity{
    private String[] listTitles;
    private ListView mDrawerListView;
    private View newView;
    SeveralButtonsConstructor severalButtonsConstructor;
    private ElementsCreator elementsCreator;
    private JoysticChanger joysticChanger;
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
            //  layout.addView(buttonConstruct.createElementInMainScreen(MainActivity.this));

            construct.createCreatingScreen(layout,ElementCreatingActivity.this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ((TextView)findViewById(R.id.textView)).setText("Потяните вправо чтобы открыть меню");
        cr();
    }
    private void cr(){
        Button button=(Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInFile();
                startActivity(new Intent(ElementCreatingActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }


    //Как раз эта функция и будет вызываться


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
                    case 2: elementsCreator.createTextEnteringDialog(newView,1,0);
                        break;
                    case 3: elementsCreator.chooseColorDialog(newView);
                        break;
                    case 4: elementsCreator.createComandEditingDialog(newView,1,0);;
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

                ((JoysticView)newView).setEnabled(false);
                switch (position) {
                    case 0:joysticChanger.proportionsChanging(newView);
                        break;
                    case 1: joysticChanger.clickPositionChanging(newView);
                        break;
                    case 2: joysticChanger.chooseBorderColorDialog(newView);
                        break;
                    case 3: joysticChanger.chooseButtonColorDialog(newView);
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
                    case 3: elementsCreator.createTextEnteringDialog(newView,((LinearLayout)newView).getChildCount(),0);
                        break;
                    case 4: elementsCreator.chooseColorDialog(newView);
                        break;
                    case 5: elementsCreator.createComandEditingDialog(newView,((LinearLayout)newView).getChildCount(),0);;
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
                elementsCreator=new ElementsCreator(layout,ElementCreatingActivity.this);
                newView= elementsCreator.createButtons(1);
                severalButtonsConstructor =new SeveralButtonsConstructor(1);
                createDrawer();
                break;
            case elButton2:
                elementsCreator=new ElementsCreator(layout,ElementCreatingActivity.this);
                newView= elementsCreator.createButtons(2);
                severalButtonsConstructor =new SeveralButtonsConstructor(2);
                createDrawer2();
                break;
            case elButton3:
                elementsCreator=new ElementsCreator(layout,ElementCreatingActivity.this);
                newView= elementsCreator.createButtons(3);
                severalButtonsConstructor =new SeveralButtonsConstructor(3);
                createDrawer2();
                break;
            case elButton4:
                elementsCreator=new ElementsCreator(layout,ElementCreatingActivity.this);
                newView= elementsCreator.createButtons(4);
                severalButtonsConstructor =new SeveralButtonsConstructor(4);
                createDrawer2();
                break;
            case elJoy:
                joysticChanger=new JoysticChanger(layout,ElementCreatingActivity.this);
                newView= joysticChanger.createJoy();
                 newView.setClickable(false);
                createDrawerForJoy();
                //severalButtonsConstructor=new SeveralButtonsConstructor(4);
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
                if(element.equalsIgnoreCase(elJoy))
                ((JoysticView)newView).setEnabled(true);
                if(joysticChanger==null)
                elementsCreator.setNullTochListeners(newView);
                return false;
            }else {
                startActivity(new Intent(ElementCreatingActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveInFile(){

        String redisString = null;

        if(construct==null)
            construct=new AllConstruct();
        if (severalButtonsConstructor!=null)
        construct.addNewConstruct(sevBtnConstr());
        if(joysticChanger!=null)
        construct.addNewConstruct(joyConstr());
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

    public SeveralButtonsConstructor sevBtnConstr(){
        //RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)newView.getLayoutParams();
        severalButtonsConstructor.setHeight(newView.getHeight());
        severalButtonsConstructor.setWidth(newView.getWidth());
        severalButtonsConstructor.setX(newView.getX());
        severalButtonsConstructor.setY(newView.getY());
        severalButtonsConstructor.setOrientation(((LinearLayout)newView).getOrientation());
        String[] strings=new String[((LinearLayout) newView).getChildCount()];
        for(int i=0;i<((LinearLayout) newView).getChildCount();i++)
            strings[i]=((Button)((LinearLayout) newView).getChildAt(i)).getText().toString();
            severalButtonsConstructor.setText(strings);
        severalButtonsConstructor.setColor(elementsCreator.getColour());
        severalButtonsConstructor.setComand(elementsCreator.getComands());
        return severalButtonsConstructor;
    }

    public JoysticConstructor joyConstr(){
        //RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)newView.getLayoutParams();
        JoysticConstructor joysticConstructor=new JoysticConstructor();
        joysticConstructor.setHeight(newView.getHeight());
        joysticConstructor.setWidth(newView.getWidth());
        joysticConstructor.setX(newView.getX());
        joysticConstructor.setY(newView.getY());
        joysticConstructor.setBorderColour(((JoysticView)newView).getBorderColor());
        joysticConstructor.setButtonColour(((JoysticView)newView).getButtonColor());
        return joysticConstructor;
        //return joysticChanger.getJoysticConstructor();
    }
}
