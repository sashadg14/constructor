package com.example.alex.navigationdrawerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ElementsPickActivity extends AppCompatActivity implements View.OnClickListener{
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBoxJoy;
    CheckBox checkBoxBtnJoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_pick);
        inicializeCheckBoxes();
    }

    private void inicializeCheckBoxes(){
        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox4=(CheckBox)findViewById(R.id.checkBox4);
        checkBoxJoy=(CheckBox)findViewById(R.id.checkBoxJoy);
        checkBoxBtnJoy=(CheckBox)findViewById(R.id.checkBoxBtnJoy);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBoxJoy.setOnClickListener(this);
        checkBoxBtnJoy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ElementsPickActivity.this,MainActivity.class);
        switch (v.getId()){
            case R.id.checkBox1:
                intent.putExtra("element","button1");
                break;
            case R.id.checkBox2:
                intent.putExtra("element","button2");
                break;
            case R.id.checkBox3:
                intent.putExtra("element","button3");
                break;
            case R.id.checkBox4:
                intent.putExtra("element","button4");
                break;
            case R.id.checkBoxJoy:
                intent.putExtra("element","joy");
                break;
            case R.id.checkBoxBtnJoy:
                intent.putExtra("element","btn_joy");
                break;
        }
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
