package com.example.puff.finalproject.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.sharedPrefrences.InitializePref;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CollegeDetails extends AppCompatActivity {
    TextView clg_name,clg_info,courses;
    ImageView clg_image;
    String college_name;
    JSONArray agents;
    JSONArray college_info,courseArray;
    SharedPreferences sharedPreferences;

    private static String UPLOAD_URL = "https://alishakapoor22895.000webhostapp.com/student/collegeDetails.php";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_details);
        Intent intent = getIntent();
        college_name = intent.getStringExtra("College");
        clg_name = (TextView)findViewById(R.id.txt_college);
        clg_info = (TextView)findViewById(R.id.txt_info);
        courses = (TextView)findViewById(R.id.txt_course);
        clg_image = (ImageView)findViewById(R.id.img_college);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        sharedPreferences = getSharedPreferences(InitializePref.myPrefrence,this.MODE_PRIVATE);
        getCollegeInfo();

    }
    public void processJson(String s) {
        try{
        JSONObject object = new JSONObject(s);
        college_info = object.getJSONArray("collegedetail");
        JSONObject college= college_info.getJSONObject(0);
        courseArray = object.getJSONArray("courses");
        agents = object.getJSONArray("agents")  ;
        clg_name.setText(college.getString("college_name"));
        clg_info.setText(college.getString("college_info"));
        String image_url =  college.getString("image");
            if(image_url.equals("")){
                Picasso.with(this)
                        .load(R.mipmap.ic_launcher)
                        .resize(250, 200)
                        .into(clg_image);
            }else{
                Picasso.with(this)
                        .load(image_url).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                        .resize(250, 200)
                        .into(clg_image);}
        }
        catch(Exception e){}
        String course_list="";
        for(int i=0;i<courseArray.length();i++){
            try{
            course_list = course_list+courseArray.get(i)+" ";}catch(Exception e){}
        }
        courses.setText(course_list);

    }
   private ArrayList<AgentModel> getDataSet()  {
        ArrayList results = new ArrayList<AgentModel>();
        try{
            for (int index = 0; index < agents.length(); index++) {
                JSONObject json_object = agents.getJSONObject(index);
                AgentModel obj = new AgentModel(json_object.getString("agent_name"));
                results.add(index, obj);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return results;
    }
    private void getCollegeInfo(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", "onResponse: "+s);
                        processJson(s);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(CollegeDetails.this);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mAdapter = new AgentAdaptor(CollegeDetails.this,getDataSet());
                        mRecyclerView.setAdapter(mAdapter);
                        ((AgentAdaptor) mAdapter).setOnItemClickListener(new AgentAdaptor
                                .MyClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                Intent intent = new Intent(CollegeDetails.this,AgentDetails.class);
                                try{
                                intent.putExtra("Agent",agents.getJSONObject(position).getString("agent_name"));}catch(Exception e){}
                                startActivity(intent);
                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {


                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("college", college_name);

                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
