package com.example.puff.finalproject.student;

import java.util.Arrays;

/**
 * Created by deeptansh on 31/3/17.
 */

public class CollegeModel {
    private String college;
    private String course;
    private String image;

    public CollegeModel(String college, String course, String image) {
        this.college = college;
        this.course = course;
        this.image = image;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
