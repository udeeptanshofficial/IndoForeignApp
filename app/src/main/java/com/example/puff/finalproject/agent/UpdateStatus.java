package com.example.puff.finalproject.agent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puff.finalproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateStatus extends AppCompatActivity {
    String application;
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
        Toast.makeText(this, "fnuds", Toast.LENGTH_SHORT).show();
    }

}
