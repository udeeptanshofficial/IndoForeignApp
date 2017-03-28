package com.example.puff.finalproject.student;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.puff.finalproject.R;

import org.w3c.dom.Text;

public class Try extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    TextView t1,t2,t3;
    CheckBox c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView4);
        t3=(TextView)findViewById(R.id.textView5);
        c1=(CheckBox)findViewById(R.id.check2);
        c2=(CheckBox)findViewById(R.id.check3);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        email = (EditText) findViewById(R.id.email);
    }

    public void onClick(View view)
    {

        if(c1.isChecked())
        {
            String value=c1.getText().toString();
            t3.setText(value);
        }
        if(c2.isChecked())
        {
            String value=c2.getText().toString();
            t3.setText(value);
        }
        if(c1.isChecked() && c2.isChecked())
        {
            String value1=c1.getText().toString();
            String value2=c2.getText().toString();
            String value3=value1.concat(value2);
            t3.setText(value3);
        }
        if(!c1.isChecked() && !c2.isChecked())
        {
            t3.setText("");
        }
        //sendEmail();
    }

   /* boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"testmail@testmail.com"});
    }*/
   /* public void sendEmail()
    {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        //emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText());
        //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailbody.getText());
        this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }*/

}