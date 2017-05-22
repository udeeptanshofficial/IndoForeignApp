package com.example.puff.finalproject.agent;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.sharedPrefrences.InitializePref;
//import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeptansh on 26/4/17.
 */
@TargetApi(23)
public class UpdateDialog extends DialogFragment {
    SharedPreferences sharedpreference;
    String playerid,process;
    String updateCol,message,status,studentname;
    EditText etmsg;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Context context;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        sharedpreference = this.getActivity().getSharedPreferences(UpdateStatus.myPrefrence, this.getActivity().MODE_PRIVATE);
        playerid = sharedpreference.getString("playerid","");
        process = sharedpreference.getString("process","");
        studentname = sharedpreference.getString("student","");
        Log.d("TAG", "onCreateDialog: "+playerid+process);
        // Get the layout inflater
        setColumnName();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View promptView = inflater.inflate(R.layout.dialog_update,null);

        builder.setTitle("Update Status");

        builder.setView(promptView)
                // Add action buttons
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etmsg = (EditText) promptView.findViewById(R.id.edit);
                        radioGroup = (RadioGroup) promptView.findViewById(R.id.radiogroup) ;
                        message = etmsg.getText().toString();
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton) promptView.findViewById(selectedId);
                        if(radioButton.getText().equals("Accept")){status="ok";}
                        else if(radioButton.getText().equals("Reject")){status="no";}
                        updateStatus();
                    }
                });

        return builder.create();
    }

    public void setColumnName(){
        if(process.equals("Application Accepted")){
            updateCol = "accepted";
        }
        else if(process.equals("Documents Checked")){
            updateCol = "document_check";
        }
        else if(process.equals("Exam Scores Verified")){
            updateCol = "band_check";
        }
        else if(process.equals("Application Forwarded To College")){
            updateCol = "application_fwd";
        }
        else if(process.equals("Response Received")){
            updateCol = "response_recieved";
        }
        else if(process.equals("Visa Checked")){
            updateCol = "visa_check";
        }
        else if(process.equals("Fee Submission Done")){
            updateCol = "fee_submitted";
        }
        else if(process.equals("Joining Letter Sent")){
            updateCol = "joining_letter";
        }
        else if(process.equals("Confirmation From Student")){
            updateCol = "student_confirmation";
        }
        else if(process.equals("College Joined By Student")){
            updateCol = "college_join";
        }

    }
    public void updateStatus(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/agent/updateStatus.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               /* try {
                    String test_message = "Your applivation has a new update "+process;
                    OneSignal.postNotification(new JSONObject("{'contents': {'en':'"+test_message+"'}, 'include_player_ids': ['" + playerid + "']}"),
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
                }*/

                try{Intent intent = new Intent(getActivity().getApplicationContext(),StudentsApplication.class);
                startActivity(intent);}catch(Exception e){
                    Log.d("TAG", "onResponse: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("student", studentname);
                map.put("status", status);
                map.put("process", updateCol);
                map.put("message", message);

                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
    }
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }

}
