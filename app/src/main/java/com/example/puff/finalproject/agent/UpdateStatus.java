package com.example.puff.finalproject.agent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import com.example.puff.finalproject.student.Login;
import com.example.puff.finalproject.student.MainDrawer;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateStatus extends AppCompatActivity {
    String application,playerid,studentname,notifyProcess;
    ImageView userImage;
    ListView listView;
    TextView student,college,course,curr_process;
    ArrayList process;
    ArrayList status;
    JSONObject object;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        Intent intent = getIntent();
        application = intent.getStringExtra("status");
        userImage = (ImageView) findViewById(R.id.imgProfilePicture);
        student = (TextView)findViewById(R.id.txt_name);
        college = (TextView)findViewById(R.id.txt_college);
        course = (TextView)findViewById(R.id.txt_course);
        listView = (ListView)findViewById(R.id.list_view) ;
        curr_process = (TextView)findViewById(R.id.textView10);
        update = (Button) findViewById(R.id.btn_update);

        setContent();
    }
    public void setContent(){

        try{
            object = new JSONObject(application);
            studentname = object.getString("student_name");
            student.setText(object.getString("student_name"));
            college.setText(object.getString("college"));
            course.setText(object.getString("course"));
            String image_url = object.getString("image");
            if(image_url.equals("")){
                Picasso.with(this)
                        .load(R.mipmap.user)
                        .resize(250, 200)
                        .into(userImage);
            }else{
                Picasso.with(this)
                        .load(image_url).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                        .resize(250, 200)
                        .into(userImage);}
            process = new ArrayList();
            status = new ArrayList();
            process.add("Application Accepted");
            process.add("Documents Checked");
            process.add("Exam Scores Verified");
            process.add("Application Forwarded To College");
            process.add("Response Received");
            process.add("Visa Checked");
            process.add("Fee Submission Done");
            process.add("Joining Letter Sent");
            process.add("Confirmation From Student");
            process.add("College Joined By Student");
            status.add(object.getString("accepted"));
            status.add(object.getString("document_check"));
            status.add(object.getString("band_check"));
            status.add(object.getString("application_fwd"));
            status.add(object.getString("response_recieved"));
            status.add(object.getString("visa_check"));
            status.add(object.getString("fee_submitted"));
            status.add(object.getString("joining_letter"));
            status.add(object.getString("student_confirmation"));
            status.add(object.getString("college_join"));
            setListData();
            UpdateAdapter updateAdapter = new UpdateAdapter(this,R.layout.list_status,setListData());
            listView.setAdapter(updateAdapter);





        }catch(JSONException e){}


    }

    public ArrayList<UpdateModel> setListData(){
        ArrayList<UpdateModel> data = new ArrayList<>();
        String temp;
        for(int index = 0;index<process.size();index++){
            temp = status.get(index).toString();
            if(temp.equals("no") || temp.equals("")){
                notifyProcess = process.get(index).toString();
                curr_process.setText(process.get(index).toString());
                update.setVisibility(View.VISIBLE);
                break;
            }
            else{
                UpdateModel model =new UpdateModel(process.get(index).toString(),status.get(index).toString());
                data.add(index,model);
            }


        }
        return data;
    }
    public void openDialog(View view){
        getplayerid();

    }
    SharedPreferences sharedPreferences;
    public static final String myPrefrence="Notification";
    public void getplayerid(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/agent/getplayerid.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                playerid=response;
                sharedPreferences = UpdateStatus.this.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("playerid",playerid);
                editor.putString("process",notifyProcess);
                editor.putString("student",studentname);
                editor.commit();
                UpdateDialog updateDialog = new UpdateDialog();
                updateDialog.show(getFragmentManager(),"passed value");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", studentname);

                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}
