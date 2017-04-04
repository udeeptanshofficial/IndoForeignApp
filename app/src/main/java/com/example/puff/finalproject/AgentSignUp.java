package com.example.puff.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.student.MainDrawer;
import com.example.puff.finalproject.student.Qualification;
import com.example.puff.finalproject.student.SignUp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView t1,t2,t3,t4,t5;
    EditText e1,e2,e3,e4,e5,e6;
    Spinner spinner;
    Button btn;

    final String AgentSignup_URL = "https://alishakapoor22895.000webhostapp.com/agentsignup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_sign_up);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);

        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);

        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.s1);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Jalandhar");
        categories.add("Chandigarh");
        categories.add("New Delhi");
        categories.add("Ludhiana");
        categories.add("Amritsar");
        categories.add("[Choose City]");
        final int list=categories.size()-1;
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner1_item, categories)
        {
            public int getCount() {
                return(list); // Truncate the list
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(list);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {
        if(v==btn)
        {
            agentSignup();
        }
    }
    public void agentSignup()
    {
        if(isInvalid())
        {
            return;
        }
        final String fname=e6.getText().toString();
        final String oname=e1.getText().toString();
        final String email=e3.getText().toString();
        final String password=e4.getText().toString();
        final String contact=e2.getText().toString();
        final String city=spinner.getSelectedItem().toString();
        StringRequest stringRequest;
        final ProgressDialog loading = ProgressDialog.show(this, "Please Wait.....", "Registering......", false, false);
        stringRequest = new StringRequest(Request.Method.POST, AgentSignup_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        if (response.equals("success")) {
                            loading.dismiss();
                            //Toast.makeText(getApplicationContext(), "User Registration Success....", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainDrawer.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(AgentSignUp.this, "User Registration Failed...", Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(AgentSignUp.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firm_name", fname);
                params.put("owner_name", oname);
                params.put("email", email);
                params.put("password", password);
                params.put("contact",contact);
                params.put("contact_additional",city);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public boolean isInvalid()
    {
        String getfirmname = e6.getText().toString();
        String getownername = e1.getText().toString();
        String getemail = e3.getText().toString();
        String getpassword = e4.getText().toString();
        String getmobile = e2.getText().toString();
        String getcity = spinner.getSelectedItem().toString();

        if (getfirmname.isEmpty()) {
            e6.setError("Enter Firm Name");
            return true;
        }
        if (getownername.isEmpty()) {
            e1.setError("Enter Owner Name");
            return true;
        }
        if (getpassword.isEmpty()) {
            e4.setError("Enter Password");
            return true;
        }
        if (getemail.isEmpty()) {
            e3.setError("Enter Email");
            return true;
        }
        if (getmobile.isEmpty()) {
            e2.setError("Enter mobile number");
            return true;
        }
        CharSequence temp_emilID = e3.getText().toString();//here username is the your edittext object...
        if (!isValidEmail(temp_emilID)) {
            e3.requestFocus();
            e3.setError("Enter Correct Mail_ID ..!!");
            return true;
        }
        return false;
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
