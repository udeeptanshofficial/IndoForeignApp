package com.example.puff.finalproject.student;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puff.finalproject.R;

import java.util.List;

/**
 * Created by deeptansh on 7/4/17.
 */

public class ApplicationAdapter extends ArrayAdapter<ApplicationModel> {
    List<ApplicationModel> data;
    public ApplicationAdapter(Context context, @LayoutRes int resource, List<ApplicationModel> data) {
        super(context, resource);
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ApplicationModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ApplicationModel applicationModel = data.get(position);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.application_list, parent, false);

        TextView agent = (TextView) rowView.findViewById(R.id.txt_firm);
        TextView college = (TextView) rowView.findViewById(R.id.txt_college);
        ImageView status = (ImageView) rowView.findViewById(R.id.img_status);
        agent.setText(applicationModel.getAgent());
        college.setText(applicationModel.getCollege());
        int stat = Integer.parseInt(applicationModel.getStatus());
        switch (stat){
            case 10: status.setImageResource(R.mipmap.ten);
                        break;
            /*case 20: status.setBackground();
                break;
            case 30: status.setBackground();
                break;
            case 40: status.setBackground();
                break;
            case 50: status.setBackground();
                break;
            case 60: status.setBackground();
                break;
            case 70: status.setBackground();
                break;
            case 80: status.setBackground();
                break;
            case 90: status.setBackground();
                break;
            case 100: status.setBackground();
                break;*/

        }
        return rowView;
    }
}
