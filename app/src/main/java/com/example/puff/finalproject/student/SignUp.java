package com.example.puff.finalproject.student;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Calendar calendar;
    private int year, month, day;
    TextView fn,ln,email,pass,gender,dob;
    EditText oe1,oe2,oe3,oe4,oe5;
    Button ob;
    private RadioButton or3;
    private RadioButton or1,or2;
    private RadioGroup radioGroup;

    Toolbar tool;

    final String Signup_URL = "https://alishakapoor22895.000webhostapp.com/Signup.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tool=(Toolbar)findViewById(R.id.toolbar);
        tool.setTitle("INDO FOREIGN");
        fn=(TextView)findViewById(R.id.t1);
        ln=(TextView)findViewById(R.id.t2);
        email=(TextView)findViewById(R.id.t3);
        pass=(TextView)findViewById(R.id.t4);
        gender=(TextView)findViewById(R.id.t5);
        dob=(TextView)findViewById(R.id.t6);

        ob=(Button)findViewById(R.id.btn);

        oe1=(EditText)findViewById(R.id.e1);
        oe2=(EditText)findViewById(R.id.e2);
        oe3=(EditText)findViewById(R.id.e3);
        oe4=(EditText)findViewById(R.id.e4);
        oe5=(EditText)findViewById(R.id.e5);


        or1=(RadioButton)findViewById(R.id.r1);
        or2=(RadioButton)findViewById(R.id.r2);
        radioGroup=(RadioGroup)findViewById(R.id.r);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }
    public void setDate(View view) {
        showDialog(999);
       // Toast.makeText(getApplicationContext(), "",
                //Toast.LENGTH_SHORT)
                //.show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        oe5.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onClick(View view)
    {
        if(inValid()){
            return;
        }
        if(radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

            return;
        }
        registerNewUser();
    }


    public void registerNewUser() {

        final String fname=oe1.getText().toString();
        final String lname=oe2.getText().toString();
        final String email=oe3.getText().toString();
        final String password=oe4.getText().toString();
        final String dob=oe5.getText().toString();

        int SelectedId=radioGroup.getCheckedRadioButtonId();
        or3=(RadioButton)findViewById(SelectedId);
        final String getGender=or3.getText().toString();
        final String rdoMale=or1.getText().toString();
        final String rdoFeMale=or2.getText().toString();
        StringRequest stringRequest;
        final ProgressDialog loading = ProgressDialog.show(this, "Please Wait.....", "Registering......", false, false);
        stringRequest = new StringRequest(Request.Method.POST, Signup_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        if (response.equals("success")) {
                            loading.dismiss();
                            //Toast.makeText(getApplicationContext(), "User Registration Success....", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Qualification.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(SignUp.this, "User Registration Failed...", Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", fname);
                params.put("last_name", lname);
                params.put("email", email);
                params.put("password", password);
                params.put("gender",getGender);
                params.put("dob", dob);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean inValid() {
        String getfullname = oe1.getText().toString();
        String getname = oe2.getText().toString();
        String getemail = oe3.getText().toString();
        String getpassword = oe4.getText().toString();
        String getgenderMale = or1.getText().toString();
        String getgenderFemale = or2.getText().toString();
        String getdob = oe5.getText().toString();

        if (getfullname.isEmpty()) {
            oe1.setError("Enter First Name");
            return true;
        }
        if (getname.isEmpty()) {
            oe2.setError("Enter Last Name");
            return true;
        }
        if (getpassword.isEmpty()) {
            oe4.setError("Enter Password");
            return true;
        }
        if (getemail.isEmpty()) {
            oe3.setError("Enter Email");
            return true;
        }
        if (getgenderMale.isEmpty()) {
            or1.setError("Select Gender");
            return true;
        }
        if (getgenderFemale.isEmpty()) {
            or2.setError("Select Gender");
            return true;
        }
        if (getdob.isEmpty()) {
            oe5.setError("Enter DOB");
            return true;
        }
        CharSequence temp_emilID = oe3.getText().toString();//here username is the your edittext object...
        if (!isValidEmail(temp_emilID)) {
            oe3.requestFocus();
            oe3.setError("Enter Correct Mail_ID ..!!");
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
