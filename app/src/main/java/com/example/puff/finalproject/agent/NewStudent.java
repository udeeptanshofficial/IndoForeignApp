package com.example.puff.finalproject.agent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.puff.finalproject.R;

import java.util.ArrayList;

public class NewStudent extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    protected void onResume() {
        super.onResume();
        ((RecyclerAdapter) mAdapter).setOnItemClickListener(new RecyclerAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObjectNew> getDataSet() {
        ArrayList results = new ArrayList<DataObjectNew>();
        for (int index = 0; index < 20; index++) {
            DataObjectNew obj = new DataObjectNew("Name" + index,
                    "College " + index, "Course"+index ,"image" +index);
            results.add(index, obj);
        }
        return results;
    }
}
