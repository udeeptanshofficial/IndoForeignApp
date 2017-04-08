package com.example.puff.finalproject.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puff.finalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackApplication extends AppCompatActivity {
    String response;
    TextView agentStatus,docCheck,bandCheck,applicationFwd,responseRec,visaCheck,feeSubmit,joiningLetter,studentConf,collegeJoin;
    ImageView imgagentstatus,imgdoccheck,imgbandcheck,imgapplicationfwd,imgresponserecieved,imgvisacheck,imgfeesubmit,imgjoiningletter,imgstudentconf,imgcollegejoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_application);
        Intent intent = getIntent();
        response = intent.getStringExtra("status");
        agentStatus = (TextView)findViewById(R.id.agent_status);
        imgagentstatus = (ImageView)findViewById(R.id.img_agentstatus);
        setFields(response);

    }
    private void setFields(String response){
        String temp;
        try{
            JSONObject jsonObject = new JSONObject(response);
            Log.d("TAG", "setFields: "+jsonObject);
            temp = jsonObject.getString("accepted");
            if(temp.equals("yes")){
                agentStatus.setText("Application Accepted");
                imgagentstatus.setImageResource(R.mipmap.complete);
            }
            else if (temp.equals("no")){
                agentStatus.setText("Application Rejected");
                imgagentstatus.setImageResource(R.mipmap.rejected);
            }
            else {
                agentStatus.setText("Application On Hold");
                imgagentstatus.setImageResource(R.mipmap.waiting);
            }
            /*temp = jsonObject.getString("document_check");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("band_check");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("application_fwd");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("response_recieved");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("visa_check");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("fee_submitted");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("joining_letter");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("student_confirmation");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }
            temp = jsonObject.getString("college_join");
            if(temp.equals("ok")){

            }else if(temp.equals("pending")){

            }else if(temp.equals("rejected")){

            }*/



        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
