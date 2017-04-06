package com.example.puff.finalproject.agent;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.puff.finalproject.R;

public class ViewDocument extends AppCompatActivity {
    CheckBox marksheet_10;
    CheckBox marksheet_12;
    CheckBox marksheet_graduation;

    Button download_1;
    Button download_2;
    Button download_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_document);
        marksheet_10 = (CheckBox) findViewById(R.id.c1);
        marksheet_12 = (CheckBox) findViewById(R.id.c2);
        marksheet_graduation = (CheckBox) findViewById(R.id.c3);
        download_1 = (Button) findViewById(R.id.b1);
        download_2 = (Button) findViewById(R.id.button);
        download_3 = (Button) findViewById(R.id.b3);

    }
    public void check(View v){
        if(marksheet_10.isChecked()) {download_1.setVisibility(View.VISIBLE);download_1.setBackgroundColor(Color.CYAN);}
        else {
            download_1.setClickable(false);download_1.setVisibility(View.INVISIBLE);
        }
        if(marksheet_12.isChecked()) {download_2.setVisibility(View.VISIBLE);download_2.setBackgroundColor(Color.CYAN);}
        else {
            download_2.setClickable(false);download_2.setVisibility(View.INVISIBLE);
        }
        if(marksheet_graduation.isChecked()) {download_3.setVisibility(View.VISIBLE);download_3.setBackgroundColor(Color.CYAN);}
        else {
            download_3.setClickable(false);download_3.setVisibility(View.INVISIBLE);
        }
    }
}
