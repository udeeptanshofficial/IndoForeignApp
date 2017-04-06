package com.example.puff.finalproject.student;

/**
 * Created by deeptansh on 5/4/17.
 */

public class RatingModel {
    private String name;
    private String rating;
    private String review;

    public RatingModel(String name, String rating, String review) {
        this.name = name;
        this.rating = rating;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
