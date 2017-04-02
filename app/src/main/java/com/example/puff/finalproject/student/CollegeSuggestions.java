package com.example.puff.finalproject.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Arrays;

import static android.app.ProgressDialog.show;

public class CollegeSuggestions extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private String UPLOAD_URL = "https://alishakapoor22895.000webhostapp.com/student/suggested.php";
    private String COLLEGE = "college";
    private String COURSE ="courses" ;
    private JSONArray collegeArray;
    private JSONArray courseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_suggestions);
        getUserdata();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }




    String courses=" ";
    private ArrayList<CollegeModel> getDataSet()  {
        ArrayList results = new ArrayList<CollegeModel>();
        try{
            for (int index = 0; index < collegeArray.length(); index++) {
                JSONObject json_object = collegeArray.getJSONObject(index);
                JSONArray temp =  courseArray.getJSONArray(index);
                for(int i=0;i<temp.length();i++){
                    courses = courses+temp.get(i)+" ";
                }
                CollegeModel obj = new CollegeModel(json_object.getString("college_name"), courses,json_object.getString("image"));
                courses=" ";
                results.add(index, obj);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return results;
    }
    private void getUserdata(){
        final ProgressDialog loading = show(this,"importing data...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try{processJson(s);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(CollegeSuggestions.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new SuggestionAdaptor(CollegeSuggestions.this,getDataSet());
                            mRecyclerView.setAdapter(mAdapter);
                            ((SuggestionAdaptor) mAdapter).setOnItemClickListener(new SuggestionAdaptor
                                    .MyClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {
                                    Log.i(LOG_TAG, " Clicked on Item " + position);
                                    Intent intent  = new Intent(CollegeSuggestions.this,CollegeDetails.class) ;
                                    startActivity(intent);
                                }
                            });
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        loading.dismiss();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();

                        Toast.makeText(CollegeSuggestions.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public void processJson(String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);
        collegeArray = jsonObject.getJSONArray(COLLEGE);
        courseArray = jsonObject.getJSONArray(COURSE);


    }
}
