package com.example.puff.finalproject.agent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.sharedPrefrences.InitializePref;
import com.example.puff.finalproject.student.ApplicationAdapter;
import com.example.puff.finalproject.student.ApplicationList;
import com.example.puff.finalproject.student.ApplicationModel;
import com.example.puff.finalproject.student.TrackApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentsApplication extends AppCompatActivity {

    SharedPreferences sharedpreference;
    ListView listView;
    JSONArray applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_application);
        sharedpreference = getSharedPreferences(InitializePref.myPrefrence, this.MODE_PRIVATE);
        getApplicationList();
        listView = (ListView)findViewById(R.id.list_view);
        // listView2 = (ListView) findViewById(R.id.list_view);

    }

    public void getApplicationList(){
        final String agent= sharedpreference.getString("Agent_name","");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/agent/applicationList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(StudentsApplication.this,s, Toast.LENGTH_LONG).show();
                        Log.d("TAG", "onResponse: "+s);
                        processResponse(s);
                        ApplicationAdapter applicationAdapter = new ApplicationAdapter(StudentsApplication.this,R.layout.rating_list,setListAdapter());
                        listView.setAdapter(applicationAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent  = new Intent(StudentsApplication.this,TrackApplication.class);
                                try{
                                    intent.putExtra("status",applications.getJSONObject(position).toString());}catch(Exception e){}
                                startActivity(intent);
                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(StudentsApplication.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("agent_name",agent);
                return map;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
    public void processResponse(String s){
        try{
            JSONObject jsonObject = new JSONObject(s);
            applications = jsonObject.getJSONArray("application_list");
        }catch(Exception e){}

    }
    public ArrayList<ApplicationModel> setListAdapter(){
        ArrayList<ApplicationModel> data = new ArrayList<>();
        int stat=0;
        String agent,college,status;
        try {
            for(int index=0;index<applications.length();index++){

                JSONObject temp = applications.getJSONObject(index);
                agent = temp.getString("student_name");
                college = temp.getString("college");
                if(temp.getString("accepted").equals("yes")) stat +=10;
                if(temp.getString("document_check").equals("ok")) stat +=10;
                if(temp.getString("band_check").equals("ok")) stat +=10;
                if(temp.getString("application_fwd").equals("ok")) stat +=10;
                if(temp.getString("response_recieved").equals("ok")) stat +=10;
                if(temp.getString("visa_check").equals("ok")) stat +=10;
                if(temp.getString("fee_submitted").equals("ok")) stat +=10;
                if(temp.getString("joining_letter").equals("ok")) stat +=10;
                if(temp.getString("student_confirmation").equals("ok")) stat +=10;
                if(temp.getString("college_join").equals("ok")) stat +=10;
                status = String.valueOf(stat);
                ApplicationModel model = new ApplicationModel(agent,college,status);
                data.add(index,model);
                stat =0;



            }


        }catch (JSONException e){}
        return data;

    }

}
