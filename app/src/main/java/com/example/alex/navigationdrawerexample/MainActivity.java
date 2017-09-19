package com.example.alex.navigationdrawerexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private String[] listTitles;
    private ListView mDrawerListView;
    private View newView;
    RelativeLayout relativeLayoutToolbar;
    RelativeLayout layout;
    private String element="",elButton1="button1",elButton2="button2",elButton3="button3",elJoy="joy",elBtnJoy="btn_joy";
    private Button txtButton;
    private Button sizeButton;
    private Button actionButton;
    private Button colorButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayoutToolbar =(RelativeLayout)findViewById(R.id.rel_tollbar);
        layout =(RelativeLayout) findViewById(R.id.rel_screen);

        element=getIntent().getStringExtra("element");
        listTitles = getResources().getStringArray(R.array.comands);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        createElement();
        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, listTitles));

        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: startActivity(new Intent(MainActivity.this,ElementsPickActivity.class));
                    case 1:
                }
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
            }
        });
        View toolbar=getLayoutInflater().inflate(R.layout.toolbar, null);

        txtButton=(Button)toolbar.findViewById(R.id.txtButton);
        sizeButton=(Button)toolbar.findViewById(R.id.sizeButton);
        actionButton =(Button)toolbar.findViewById(R.id.actionButton);
        colorButton=(Button)toolbar.findViewById(R.id.colorButton);


    }
        private void createElement(){
            if(element==null||element.equalsIgnoreCase(""))
                return;
            //if(element.equalsIgnoreCase(elButton1))
            View toolbar=getLayoutInflater().inflate(R.layout.toolbar, null);

            relativeLayoutToolbar.addView(toolbar);

            (toolbar.findViewById(R.id.txtButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createTextEnteringDialog();
                }
            });

            (toolbar.findViewById(R.id.positionButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ElementsCreator(layout,newView,MainActivity.this).clickPositionChanging(newView);
                }
            });

            (toolbar.findViewById(R.id.sizeButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ElementsCreator(layout,newView,MainActivity.this).proportionsChanging(newView);
                    //Toast.makeText(getApplication(),"fdsfadsasdf",Toast.LENGTH_LONG).show();
                }
            });
            switch (element){

                case "button1":
                    newView= new ElementsCreator(layout,newView,MainActivity.this).createButton();

                    break;
                case "button2":
                   // newView= new ElementsCreator(layout,newView,MainActivity.this).createButton();
            }

        }

    private AlertDialog chooseOrintationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.orientation_choose_dialog);

        builder.setItems(R.array.orientations, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                    case 1:
                }
            }
        });

        return builder.create();
    }
    private void createTextEnteringDialog() {
/* Alert Dialog Code Start*/
        layout.setOnTouchListener(null);
        newView.setOnTouchListener(null);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Введите название"); //Set Alert dialog title here

        // Set an EditText view to get user input
        final EditText input = new EditText(MainActivity.this);
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //You will get as string input data in this variable.
                // here we convert the input to a string and show in a toast.
                ((Button)newView).setText(input.getText().toString());
                //Toast.makeText(MainActivity.this,srt,Toast.LENGTH_LONG).show();
            } // End of onClick(DialogInterface dialog, int whichButton)
        }); //End of alert.setPositiveButton
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ((TextView)findViewById(R.id.textView)).setText("Нажмите \"назад\" раз чтобы сохранить");
                dialog.cancel();
            }
        }); //End of alert.setNegativeButton
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        ((TextView)findViewById(R.id.textView)).setText("Изменение текста");
       /* Alert Dialog Code End*/
        //Toast.makeText(getApplication(),"Введите название",Toast.LENGTH_LONG).show();
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

            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
