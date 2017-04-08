package com.example.puff.finalproject.student;

/**
 * Created by deeptansh on 7/4/17.
 */

public class ApplicationModel {
    private String agent;
    private String college;
    private String status;

    public ApplicationModel(String agent, String college, String status) {
        this.agent = agent;
        this.college = college;
        this.status = status;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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
