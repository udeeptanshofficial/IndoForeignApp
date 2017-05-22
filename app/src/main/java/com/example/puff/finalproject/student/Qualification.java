package com.example.puff.finalproject.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Qualification extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private int backpress;

    Toolbar tool;
    final String Signup_URL = "https://alishakapoor22895.000webhostapp.com/Qualification.php";

    Spinner spn;
    ArrayList<String> arrayl;
    ArrayAdapter adapter;

    Spinner spn1;
    ArrayList<String> arrayl1;
    ArrayAdapter adapter1;


    ArrayList<String> states;
    ArrayAdapter adapter2;
    Spinner spn2;

    Spinner spn3;
    ArrayList<String> arrayl2;
    ArrayAdapter adapter3;

    Spinner spn4;
    ArrayList arrayl3;
    ArrayAdapter adapter4;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification);

        btn = (Button) findViewById(R.id.b1);
        btn.setOnClickListener(this);

        arrayl = new ArrayList<String>();
        arrayl.add("Employed");
        arrayl.add("Student");
        arrayl.add("Unemployed");
        adapter = new ArrayAdapter(this, R.layout.spinner1_item, arrayl);
        spn = (Spinner) findViewById(R.id.s);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        tool=(Toolbar)findViewById(R.id.toolbar);
        tool.setTitle("INDO FOREIGN");

        spn1 = (Spinner) findViewById(R.id.s1);
        spn1.setOnItemSelectedListener(this);
        arrayl1 = new ArrayList<String>();
        arrayl1.add("10+2");
        arrayl1.add("Diploma");
        arrayl1.add("Graduate");
        arrayl1.add("Undergraduate");
        arrayl1.add("Masters");
        arrayl1.add("PhD");
        adapter1 = new ArrayAdapter(this, R.layout.spinner1_item, arrayl1);
        spn1.setAdapter(adapter1);


        states = new ArrayList<String>();
        states.add("Delhi");
        states.add("Goa");
        states.add("Kerala");
        states.add("Rajasthan");
        states.add("Tamil Nadu");
        states.add("Uttar Pradesh");
        states.add("Maharashtra");
        states.add("Jammu & Kashmir");
        states.add("Gujarat");
        states.add("Punjab");
        states.add("Karnatka");
        states.add("Andhra Pradesh");
        states.add("Bihar");
        states.add("Odisha");
        states.add("Madhya Pradesh");
        states.add("West Bengal");
        states.add("Assam");
        states.add("Haryana");
        states.add("Telangana");
        states.add("Uttrakhand");
        states.add("Jharkhand");
        states.add("Manipir");
        states.add("Himachal Pradesh");
        states.add("Sikkim");
        states.add("Chattisgarh");
        states.add("Nagaland");
        states.add("Tripura");
        states.add("Meghalaya");
        states.add("Arunachal Pradesh");
        states.add("Mizoram");
        adapter2 = new ArrayAdapter<String>(this, R.layout.spinner1_item, states);
        spn2 = (Spinner) findViewById(R.id.s2);
        spn2.setAdapter(adapter2);
        spn2.setOnItemSelectedListener(this);

        spn3 = (Spinner) findViewById(R.id.s3);
        spn3.setOnItemSelectedListener(this);
        arrayl2 = new ArrayList<String>();
        arrayl2.add("2017");
        arrayl2.add("2018");
        arrayl2.add("2019");
        arrayl2.add("2020");
        adapter3 = new ArrayAdapter(this, R.layout.spinner1_item, arrayl2);
        spn3.setAdapter(adapter3);

        spn4 = (Spinner) findViewById(R.id.s4);
        spn4.setOnItemSelectedListener(this);
        arrayl3 = new ArrayList<String>();
        arrayl3.add("Jalandhar");
        arrayl3.add("Mumbai");
        arrayl3.add("Delhi");
        arrayl3.add("Chandigarh");
        adapter4 = new ArrayAdapter(this, R.layout.spinner1_item, arrayl3);
        spn4.setAdapter(adapter4);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v) {
        if (v == btn) {
            addQualification();
        }
        //Intent i = new Intent(getApplicationContext(), Login.class);
        //startActivity(i);
    }

    public void addQualification()
    {
        final String getEmptype =  spn.getSelectedItem().toString();
        final String getQualification = spn1.getSelectedItem().toString();
        //final String getState = spn2.getSelectedItem().toString();
        final String getInyear = spn3 .getSelectedItem().toString();
        final String getBranch= spn4.getSelectedItem().toString();
        StringRequest stringRequest;
        final ProgressDialog loading = ProgressDialog.show(this, "Please Wait.....", "Registering......", false, false);
        stringRequest = new StringRequest(Request.Method.POST, Signup_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        if (response.equals("success")) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "User Registration Success....", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainDrawer.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(Qualification.this, "User Registration Failed...", Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Qualification.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("empType", getEmptype);
                params.put("qualification", getQualification);
                params.put("intakeYear", getInyear);
                params.put("nearestBranch",getBranch);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
 /*   public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            this.finish();
        }
    }*/
}