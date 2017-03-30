package com.example.puff.finalproject.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by deeptansh on 30/3/17.
 */

public class InitializePref {
    Context context;
    SharedPreferences sharedPreferences;
    public static final String myPrefrence="MyPrefs";
    public static final String student_name = "Student_name";
    public static final String agent_name = "Agent_name";
    SharedPreferences.Editor editor;
    public void loginStudent(Context context,String name){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(student_name,name);
        editor.commit();

    }
    public void loginAgent(Context context,String name){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(agent_name,name);
        editor.commit();

    }
    public void logout(){
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
