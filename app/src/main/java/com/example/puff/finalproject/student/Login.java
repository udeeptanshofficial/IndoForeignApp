package com.example.puff.finalproject.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private int backpress;

    Button btn,btn1;
    EditText oe1, oe2;
    TextView ot1, ot2, ot3;

    final String Login_URL = "https://alishakapoor22895.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.b1);
        btn.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.b2);
        btn1.setOnClickListener(this);

        oe1 = (EditText) findViewById(R.id.e1);
        oe2 = (EditText) findViewById(R.id.e2);
        ot1 = (TextView) findViewById(R.id.t1);
        ot2 = (TextView) findViewById(R.id.t2);
        ot3 = (TextView) findViewById(R.id.t3);
    }

    public void onClick(View v) {
        if(v==btn){
            loginUser();
        }
        if(v==btn1)
        {
            Intent i=new Intent(this,Selectone.class);
            startActivity(i);
        }

    }
    public void loginUser() {
        if(inValid()){
            return;
        }
        Toast.makeText(Login.this, "Start", Toast.LENGTH_SHORT).show();
        final String username1 = oe1.getText().toString();
        final String password1 = oe2.getText().toString();
        final ProgressDialog loading = ProgressDialog.show(this, "Please Wait....", "loggin In....", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Login_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Alisha", "response" + response);
                //Toast.makeText(Login.this, "Response", Toast.LENGTH_SHORT).show();
                if (response.equals("success")) {
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();

                    loading.dismiss();
                    Intent i = new Intent(Login.this, MainDrawer.class);
                    startActivity(i);
                } else
                {
                    Toast.makeText(Login.this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username1);
                map.put("password", password1);
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
    public boolean inValid() {
        String getfullname =  oe1.getText().toString();
        String getname = oe2.getText().toString();

        if (getfullname.isEmpty()) {
            oe1.setError("Enter Email");
            return true;
        }
        if (getname.isEmpty()) {
            oe2.setError("Enter Password");
            return true;
        }
        return false;
    }
}

