package com.example.puff.finalproject.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.ProgressDialog.show;

/**
 * Created by deeptansh on 2/4/17.
 */

public class AgentAdaptor extends RecyclerView
        .Adapter<AgentAdaptor
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<AgentModel> mDataset;
    private static MyClickListener myClickListener;
    private Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView agent;
        Button sendrequest;


        public DataObjectHolder(View itemView) {
            super(itemView);
            agent = (TextView) itemView.findViewById(R.id.txt_agentname);
            sendrequest = (Button) itemView.findViewById(R.id.btn_apply);

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

    public AgentAdaptor(Context context, ArrayList<AgentModel> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_row_agentlist, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;}



    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.agent.setText(mDataset.get(position).getAgentName());
        holder.sendrequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                sendRequest(v);
            }
        });


    }

    public void addItem(AgentModel agentModel, int index) {
        mDataset.add(index, agentModel);
        notifyItemInserted(index);
        notifyItemChanged(index);

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
    String UPLOAD_URL="https://alishakapoor22895.000webhostapp.com/student/createRequest.php";
    private void sendRequest(View v){
        final View view=v;
        final ProgressDialog loading = show(v.getContext(),"Wait while we forward your request...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
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


        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}

