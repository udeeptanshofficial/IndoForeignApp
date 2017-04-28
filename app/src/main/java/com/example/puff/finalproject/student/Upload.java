package com.example.puff.finalproject.student;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.agent.LPAgent;
import com.example.puff.finalproject.sharedPrefrences.InitializePref;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Upload.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Upload#newInstance} factory method to
 * create an instance of this fragment.
 */
@TargetApi(23)
public class Upload extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Uri path;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Upload() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Upload.
     */
    // TODO: Rename and change types and number of parameters
    public static Upload newInstance(String param1, String param2) {
        Upload fragment = new Upload();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText et10,et12,etg,et1,et2,et3,et5,et6,et7,et8,et9;
    Button browse_10,browse_12,browse_g,upload_10,upload_12,upload_g,browse_dip,upload_dip,browse_pgrad,upload_pgrad,browse_score,upload_score,browse_adhar,upload_adhar,browse_pan,upload_pan,browse_passport,upload_passport,browse_visa,upload_visa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        browse_10 = (Button) view.findViewById(R.id.b_10);
        browse_12 = (Button) view.findViewById(R.id.b_12);
        browse_g = (Button) view.findViewById(R.id.b_g);
        browse_dip=(Button)view.findViewById(R.id.b_dip);
        browse_pgrad=(Button)view.findViewById(R.id.b_postgrad);
        browse_score=(Button)view.findViewById(R.id.b_score);
        browse_adhar=(Button)view.findViewById(R.id.b_adhar);
        browse_pan=(Button)view.findViewById(R.id.b_pan);
        browse_passport=(Button)view.findViewById(R.id.b_passport);
        browse_visa=(Button)view.findViewById(R.id.b_visa);
        upload_10 = (Button) view.findViewById(R.id.b_10upload);
        upload_12 = (Button) view.findViewById(R.id.b_12uploadn);
        upload_g = (Button) view.findViewById(R.id.b_gupload);
        upload_dip=(Button)view.findViewById(R.id.b_dipupload);
        upload_pgrad=(Button)view.findViewById(R.id.b_postupload);
        upload_score=(Button)view.findViewById(R.id.b_scoreupload);
        upload_adhar=(Button)view.findViewById(R.id.b_adharupload);
        upload_pan=(Button)view.findViewById(R.id.b_panupload);
        upload_passport=(Button)view.findViewById(R.id.b_passupload);
        upload_visa=(Button)view.findViewById(R.id.b_visaupload);
        et10 = (EditText) view.findViewById(R.id.e1);
            et12 = (EditText) view.findViewById(R.id.e2);
             etg = (EditText) view.findViewById(R.id.e3);
        et1=(EditText)view.findViewById(R.id.e4);  //diploma
        et2=(EditText)view.findViewById(R.id.e5);  //pg
        et3=(EditText)view.findViewById(R.id.e6);  //score
        et5=(EditText)view.findViewById(R.id.e7);  //aadhar
        et8=(EditText)view.findViewById(R.id.e8);  //pan
        et7=(EditText)view.findViewById(R.id.e9);  //passport
        et9=(EditText)view.findViewById(R.id.e10); //visa
        browse_10.setOnClickListener(this);
        browse_12.setOnClickListener(this);
        browse_g.setOnClickListener(this);
        browse_dip.setOnClickListener(this);
        browse_pgrad.setOnClickListener(this);
        browse_score.setOnClickListener(this);
        browse_adhar.setOnClickListener(this);
        browse_pan.setOnClickListener(this);
        browse_passport.setOnClickListener(this);
        browse_visa.setOnClickListener(this);
        upload_10.setOnClickListener(this);
        upload_12.setOnClickListener(this);
        upload_g.setOnClickListener(this);
        upload_dip.setOnClickListener(this);
        upload_pgrad.setOnClickListener(this);
        upload_score.setOnClickListener(this);
        upload_adhar.setOnClickListener(this);
        upload_pan.setOnClickListener(this);
        upload_passport.setOnClickListener(this);
        upload_visa.setOnClickListener(this);
        return view;
    }
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){


            case R.id.b_10:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_10.setVisibility(View.VISIBLE);
                break;
            case R.id.b_12:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_12.setVisibility(View.VISIBLE);
                break;
            case R.id.b_g:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);
                upload_g.setVisibility(View.VISIBLE);
                break;

            case R.id.b_dip:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_dip.setVisibility(View.VISIBLE);
                break;

            case R.id.b_postgrad:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_pgrad.setVisibility(View.VISIBLE);
                break;

            case R.id.b_score:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_score.setVisibility(View.VISIBLE);
                break;

            case R.id.b_adhar:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_adhar.setVisibility(View.VISIBLE);
                break;

            case R.id.b_pan:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_pan.setVisibility(View.VISIBLE);
                break;

            case R.id.b_passport:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_passport.setVisibility(View.VISIBLE);
                break;

            case R.id.b_visa:

                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                upload_visa.setVisibility(View.VISIBLE);
                break;

            case R.id.b_10upload:
                (new UploadDocs(getActivity(), path)).execute();
                sendParams(et10.getText().toString(),"10th");
                upload_10.setBackgroundColor(Color.CYAN);
                upload_10.setText("Update");
                break;
            case R.id.b_12uploadn:

                (new UploadDocs(getActivity(), path)).execute();
                sendParams(et12.getText().toString(),"12th");
                upload_12.setBackgroundColor(Color.CYAN);
                upload_12.setText("Update");
                break;
            case R.id.b_gupload:
                sendParams(etg.getText().toString(),"");
                (new UploadDocs(getActivity(), path)).execute();
                upload_g.setBackgroundColor(Color.CYAN);
                upload_12.setText("Update");
                break;
            case R.id.b_dipupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_dip.setBackgroundColor(Color.CYAN);
                upload_dip.setText("Update");
                break;
            case R.id.b_postupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_pgrad.setBackgroundColor(Color.CYAN);
                upload_pgrad.setText("Update");
                break;
            case R.id.b_scoreupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_score.setBackgroundColor(Color.CYAN);
                upload_score.setText("Update");
                break;
            case R.id.b_adharupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_adhar.setBackgroundColor(Color.CYAN);
                upload_adhar.setText("Update");
                break;
            case R.id.b_panupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_pan.setBackgroundColor(Color.CYAN);
                upload_pan.setText("Update");
                break;
            case R.id.b_passupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_passport.setBackgroundColor(Color.CYAN);
                upload_passport.setText("Update");
                break;
            case R.id.b_visaupload:
                (new UploadDocs(getActivity(), path)).execute();
                upload_visa.setBackgroundColor(Color.CYAN);
                upload_visa.setText("Update");
                break;
        }

    }




    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                path = result.getData();
            }
        }
    }
    public void sendParams(final String name, final String type){
        StringRequest request = new StringRequest(Request.Method.POST, "https://alishakapoor22895.000webhostapp.com/uploadpdf.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("type", type);

                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
    }

}
class UploadDocs extends AsyncTask<Void, Void, Void> {
    private ProgressDialog pd;
    private Context c;
    private Uri path;

    public UploadDocs(Context c, Uri path) {
        this.c = c;
        this.path = path;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(c, "Uploading", "Please Wait");
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        pd.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        String url_path = "https://alishakapoor22895.000webhostapp.com/uploadpdf.php";
        HttpURLConnection conn = null;

        int maxBufferSize = 1024;
        try {
            URL url = new URL(url_path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setChunkedStreamingMode(1024);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data");

            OutputStream outputStream = conn.getOutputStream();
            InputStream inputStream = c.getContentResolver().openInputStream(path);

            int bytesAvailable = inputStream.available();
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, bufferSize)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.i("result", line);
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}
