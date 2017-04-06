package com.example.puff.finalproject.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApplicationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        getApplicationList();
    }
    public void getApplicationList(){
        final String student="Alisha Kapoor";   //replace with get name
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/student/applicationList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Toast.makeText(ApplicationList.this,s, Toast.LENGTH_LONG).show();
                        //Log.d("TAG", "onResponse: "+s);
                        setListAdapter(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(ApplicationList.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("student_name",student);
                return map;
            }



        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void setListAdapter(String s){

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray applications = jsonObject.getJSONArray("application_list");
            String[] agents = new String[applications.length()];
            for(int index=0;index<applications.length();index++){
                agents[index] = applications.getJSONObject(index).getString("agent_name");

            }
            /*ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, agents);*/

        }catch (JSONException e){}

    }
}
