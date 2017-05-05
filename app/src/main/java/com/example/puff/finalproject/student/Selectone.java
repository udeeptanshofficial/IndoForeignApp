package com.example.puff.finalproject.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.example.puff.finalproject.AgentSignUp;
import com.example.puff.finalproject.R;

public class Selectone extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectone);
        btn1=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(this);

        btn2=(Button)findViewById(R.id.button3);
        btn2.setOnClickListener(this);

        tool=(Toolbar)findViewById(R.id.toolbar);
        tool.setTitle("INDO FOREIGN");
    }

    @Override
    public void onClick(View v) {
        if(v==btn1)
        {
            Intent i=new Intent(this, AgentSignUp.class);
            startActivity(i);
        }
        if(v==btn2)
        {
            Intent i=new Intent(this,SignUp.class);
            startActivity(i);
        }
    }
}
