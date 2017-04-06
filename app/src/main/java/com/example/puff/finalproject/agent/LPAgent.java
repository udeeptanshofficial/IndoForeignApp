package com.example.puff.finalproject.agent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.puff.finalproject.R;

public class LPAgent extends AppCompatActivity {
    Intent changeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpagent);


    }
    public void studentList(View v){
        changeActivity = new Intent(this,StudentList.class);
        startActivity(changeActivity);
    }
    public void viewDocuments(View v){
        changeActivity = new Intent(this,ViewDocument.class);
        startActivity(changeActivity);
    }
    public void newStudent(View v){
        changeActivity = new Intent(this,NewStudent.class);
        startActivity(changeActivity);
    }
    public void updateStatus(View v){
        changeActivity = new Intent(this,UpdateStatus.class);
        startActivity(changeActivity);
    }


}
