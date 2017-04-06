package com.example.puff.finalproject.agent;

/**
 * Created by deeptansh on 20/3/17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puff.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name;
        TextView college;
        TextView course;
        ImageView imageView;


        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            college = (TextView) itemView.findViewById(R.id.txt_college);
            course = (TextView) itemView.findViewById(R.id.txt_course);
            imageView = (ImageView) itemView.findViewById(R.id.img_user) ;
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

    public MyRecyclerViewAdapter(Context context, ArrayList<DataObject> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getmText1());
        holder.college.setText(mDataset.get(position).getmText2());
        holder.course.setText(mDataset.get(position).getMtext3());
        String image_url = mDataset.get (position).getImage();
        if(image_url.equals("")){
            Picasso.with(context)
                    .load(R.mipmap.user)
                    .resize(250, 200)
                    .into(holder.imageView);
        }else{
        Picasso.with(context)
                .load(image_url).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                .resize(250, 200)
                .into(holder.imageView);}
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
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
}
