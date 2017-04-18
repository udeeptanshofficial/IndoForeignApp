package com.example.puff.finalproject.student;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.ProgressDialog.show;

public class AgentRating extends AppCompatActivity {
    RatingBar rate;
    AutoCompleteTextView auto_text;
    Button btn_submit;
    EditText review;
    SharedPreferences sharedpreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_rating);
        rate = (RatingBar)findViewById(R.id.rating);
        auto_text = (AutoCompleteTextView) findViewById(R.id.txt_auto);
        review = (EditText) findViewById(R.id.txt_review);
        getList();
        sharedpreference = getSharedPreferences(InitializePref.myPrefrence, this.MODE_PRIVATE);


    }
    String uid;
    public void submit(View view){
        OneSignal.startInit(this).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                //Log.d("debug", "User:" + userId);
                uid = userId;
                try {
                    OneSignal.postNotification(new JSONObject("{'contents': {'en':'Test Message'}, 'include_player_ids': ['" + userId + "']}"),
                            new OneSignal.PostNotificationResponseHandler() {
                                @Override
                                public void onSuccess(JSONObject response) {
                                    Log.i("OneSignalExample", "postNotification Success: " + response.toString());
                                }

                                @Override
                                public void onFailure(JSONObject response) {
                                    Log.e("OneSignalExample", "postNotification Failure: " + response.toString());
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (registrationId != null)
                    Log.d("debug", "registrationId:" + registrationId);
            }
        });
        //Log.d("TAG", "submit: "+uid);
        final String student = sharedpreference.getString("Student_name","");
        final Float value = rate.getRating();
        final String rating = String.valueOf(value);
        final String text = review.getText().toString();
        final String agent = auto_text.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/student/rating.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(AgentRating.this,s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(AgentRating.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("student_name",student);
                map.put("agent_name",agent);
                map.put("rating",rating);
                map.put("review", text);
                return map;
            }



        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);


    }
    public void getList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://alishakapoor22895.000webhostapp.com/student/getAgent.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Log.d("TAG", "onResponse: "+s);
                        processList(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(AgentRating.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void processList(String s){
        List<String> list= new ArrayList<>();
        try{
            JSONObject obj = new JSONObject(s);
            JSONArray agents = obj.getJSONArray("agents");

            for(int index=0;index<agents.length();index++){
                JSONObject temp = agents.getJSONObject(index);
                list.add(temp.getString("firm_name"));
            }

        }catch(Exception e){

        }
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        //Log.d("TAG", "processList: "+arr);
        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,arr);
        auto_text.setAdapter(adapter);
        auto_text.setThreshold(1);


    }

}
/*class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;

    }

}*/