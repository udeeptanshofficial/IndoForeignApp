package com.example.puff.finalproject.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.puff.finalproject.R;

public class Splash extends AppCompatActivity {
    Animation animrotate;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animrotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

        im=(ImageView)findViewById(R.id.img);
        im.startAnimation(animrotate);

        Thread timerThread = new Thread(){
            public void run(){
                try
                {
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}