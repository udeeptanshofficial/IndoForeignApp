package com.example.puff.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AgentSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView t1,t2,t3,t4,t5;
    EditText e1,e2,e3,e4;
    Spinner s1;
    Button btn;

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

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.s1);

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

    }
    public boolean isInvalid()
    {
        return false;
    }
}
