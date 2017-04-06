package com.example.puff.finalproject.student;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.puff.finalproject.R;

import java.util.List;

/**
 * Created by deeptansh on 5/4/17.
 */

public class RatingAdapter extends ArrayAdapter<RatingModel> {
    private List<RatingModel> rating;

    public RatingAdapter(Context context, @LayoutRes int resource, List<RatingModel> rating) {
        super(context, resource);
        this.rating = rating;
    }
    @Override
    public int getCount() {
        return rating.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RatingModel ratingModel = rating.get(position);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rating_list, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.txt_name);
        TextView rating = (TextView) rowView.findViewById(R.id.txt_rating);
        TextView review = (TextView) rowView.findViewById(R.id.txt_review);
        RatingBar ratingBar = (RatingBar)rowView.findViewById(R.id.rating);
        Float rate = Float.parseFloat(ratingModel.getRating());
        name.setText(ratingModel.getName());
        rating.setText(ratingModel.getRating());
        review.setText(ratingModel.getReview());
        ratingBar.setRating(rate);


        return rowView;
    }
}
