package com.example.puff.finalproject.agent;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.student.AgentDetails;
import com.example.puff.finalproject.student.RatingAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class ViewDocument extends AppCompatActivity {
    CheckBox marksheet_10;
    CheckBox marksheet_12;
    CheckBox marksheet_graduation;

    Button download_1;
    Button download_2;
    Button download_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_document);
        download_1 = (Button) findViewById(R.id.b1);
        download_2 = (Button) findViewById(R.id.button);
       // download_3 = (Button) findViewById(R.id.b3);

    }
    public void check(View v){
        /*if(marksheet_10.isChecked())*/ download_1.setVisibility(View.VISIBLE);download_1.setBackgroundColor(Color.CYAN);downloadTen(v);
        /*else {
            download_1.setClickable(false);download_1.setVisibility(View.INVISIBLE);
        }
        if(marksheet_12.isChecked()) {download_2.setVisibility(View.VISIBLE);download_2.setBackgroundColor(Color.CYAN);}
        else {
            download_2.setClickable(false);download_2.setVisibility(View.INVISIBLE);
        }
        if(marksheet_graduation.isChecked()) {download_3.setVisibility(View.VISIBLE);download_3.setBackgroundColor(Color.CYAN);}
        else {
            download_3.setClickable(false);download_3.setVisibility(View.INVISIBLE);
        }*/
    }
    public void downloadTen(View v){
        getFile();
    }
    public void getFile(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/downloadPdf.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", "onResponse: "+s);
                        new DownloadFile().execute(s, "10th.pdf");
                        Toast.makeText(ViewDocument.this, "File saved in storage", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {


                    }
                }){

        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
 class DownloadFile extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
        String fileName = strings[1];  // -> maven.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "IndoForeign");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            pdfFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        FileDownloader.downloadFile(fileUrl, pdfFile);
        return null;
    }
}



