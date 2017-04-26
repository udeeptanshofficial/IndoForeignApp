package com.example.puff.finalproject.agent;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puff.finalproject.R;

import java.util.List;

/**
 * Created by deeptansh on 26/4/17.
 */

public class UpdateAdapter extends ArrayAdapter<UpdateModel> {
        List<UpdateModel> data;
public UpdateAdapter(Context context, @LayoutRes int resource, List<UpdateModel> data) {
        super(context, resource);
        this.data = data;
        }
@Override
public int getCount() {
        return data.size();
        }

@Override
public UpdateModel getItem(int position) {
        return data.get(position);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent)
        {
        UpdateModel applicationModel = data.get(position);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_status, parent, false);
        int set = position+1;
        TextView number = (TextView) rowView.findViewById(R.id.txt_number);
        TextView process = (TextView) rowView.findViewById(R.id.txt_process);
        number.setText(""+set);
        process.setText(applicationModel.getProcess());

        return rowView;
        }
    }

