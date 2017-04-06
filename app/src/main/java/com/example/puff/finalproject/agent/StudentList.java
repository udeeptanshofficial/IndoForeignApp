package com.example.puff.finalproject.agent;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


import static android.app.ProgressDialog.*;


public class StudentList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private String UPLOAD_URL = "https://alishakapoor22895.000webhostapp.com/agent/connections.php";
    private String JSON_ARRAY = "users";
    private JSONArray jsonArray;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getUserdata();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        





    }



    private ArrayList<DataObject> getDataSet()  {
        ArrayList results = new ArrayList<DataObject>();
        try{
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject json_object = jsonArray.getJSONObject(index);
            DataObject obj = new DataObject(json_object.getString("student_name"),
                    json_object.getString("college"), json_object.getString("course"),json_object.getString("image"));
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
                            mLayoutManager = new LinearLayoutManager(StudentList.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter = new MyRecyclerViewAdapter(StudentList.this,getDataSet());
                            mRecyclerView.setAdapter(mAdapter);
                            ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                                    .MyClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {
                                    Log.i(LOG_TAG, " Clicked on Item " + position);
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

                        Toast.makeText(StudentList.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
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
        jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
        Log.d("TAG", "processJson: "+jsonArray);


    }
}
