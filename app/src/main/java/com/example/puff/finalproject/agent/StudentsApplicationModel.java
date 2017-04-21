package com.example.puff.finalproject.agent;

/**
 * Created by deeptansh on 21/4/17.
 */

public class StudentsApplicationModel {
    private String student;
    private String college;
    private String status;

    public StudentsApplicationModel(String student, String college, String status) {
        this.student = student;
        this.college = college;
        this.status = status;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
