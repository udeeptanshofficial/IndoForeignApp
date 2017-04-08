package com.example.puff.finalproject.agent;

/**
 * Created by deeptansh on 20/3/17.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.ProgressDialog.show;

public class RecyclerAdapter extends RecyclerView
        .Adapter<RecyclerAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObjectNew> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name;
        TextView college;
        TextView course;
        Button accept;
        Button reject;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            college = (TextView) itemView.findViewById(R.id.txt_college);
            course = (TextView) itemView.findViewById(R.id.txt_course);
            accept = (Button) itemView.findViewById(R.id.accept);
            reject = (Button) itemView.findViewById(R.id.cancel);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RecyclerAdapter(ArrayList<DataObjectNew> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_2_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        final String name=mDataset.get(position).getmText1();
        final String college= mDataset.get(position).getmText2();
        holder.name.setText(mDataset.get(position).getmText1());
        holder.college.setText(mDataset.get(position).getmText2());
        holder.course.setText(mDataset.get(position).getMtext3());
        holder.accept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                addNewStudent(v,name,college);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                removeStudent(v,name,college);
            }
        });
    }

    public void addItem(DataObjectNew dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
    String UPLOAD_URL="https://alishakapoor22895.000webhostapp.com/agent/acceptRequest.php";
    private void addNewStudent(View v,String sname,String scollege){
        final String name = sname;
        final String college = scollege;
        final View view=v;
        final ProgressDialog loading = show(v.getContext(),"processing request...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", "onResponse: "+s);
                        loading.dismiss();
                        //startActivity(Intent.getIntent());



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();

                        Toast.makeText(view.getContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("student_name", name);
                map.put("college", college);
                return map;
            }


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    String REMOVE_URL = "https://alishakapoor22895.000webhostapp.com/agent/rejectRequest.php";
    private void removeStudent(View v,String sname,String scollege){
        final String name =sname;
        final String college = scollege;
        final View view=v;
        final ProgressDialog loading = show(v.getContext(),"processing request...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REMOVE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", "onResponse: "+s);
                        loading.dismiss();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();

                        Toast.makeText(view.getContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){

                protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("student_name", name);
                map.put("college", college);
                return map;
            }


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}

