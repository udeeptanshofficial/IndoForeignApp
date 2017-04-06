package com.example.puff.finalproject.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class AgentDetails extends AppCompatActivity {
    String agent_name;
    ImageView logo;
    TextView name,about,address;
    ListView listView;
    JSONArray rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);
        Intent intent = getIntent();
        agent_name = intent.getStringExtra("Agent");
        getDetails();
        logo = (ImageView) findViewById(R.id.img_agent);
        name = (TextView) findViewById(R.id.txt_agentname);
        about = (TextView) findViewById(R.id.txt_agentinfo);
        address = (TextView) findViewById(R.id.txt_address);
        listView = (ListView) findViewById(R.id.list_view);


    }
    public void getDetails(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/student/agentDetail.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", "onResponse: "+s);
                        processResponse(s);
                        RatingAdapter ratingAdapter = new RatingAdapter(AgentDetails.this,R.layout.rating_list,setRating());
                        listView.setAdapter(ratingAdapter);

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
                params.put("agent", agent_name);

                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void processResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray =  jsonObject.getJSONArray("agent");
            rating = jsonObject.getJSONArray("ratings");
            JSONObject temp = jsonArray.getJSONObject(0);
            String image_url =  temp.getString("logo");
            if(image_url.equals("")){
                Picasso.with(this)
                        .load(R.mipmap.ic_launcher)
                        .resize(250, 200)
                        .into(logo);
            }else{
                Picasso.with(this)
                        .load(image_url).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                        .resize(250, 200)
                        .into(logo);}
            //name.setText(temp.getString("firm_name"));
            about.setText(temp.getString("about"));
            address.setText(temp.getString("address_line1")+" "+temp.getString("address_line2")+" "+temp.getString("district")+" "+temp.getString("state")+" "+temp.getString("pincode"));

        }catch(Exception e){

        }
    }
    public ArrayList<RatingModel> setRating(){
      ArrayList ratings = new ArrayList<RatingModel>();
        try{
        for(int index=0;index<rating.length();index++){

            JSONObject json_object = rating.getJSONObject(index);
            RatingModel obj = new RatingModel(json_object.getString("student_name"),json_object.getString("rating"),json_object.getString("review"));
                Log.d("TAG", "setRating: "+obj);
                ratings.add(index,obj);}

        }catch(Exception e){}
        return ratings;
    }
}
