package com.example.puff.finalproject.student;

/**
 * Created by deeptansh on 2/4/17.
 */

public class AgentModel {
    private String agentName;
    private String college;
    public AgentModel(String agentName,String course) {
        this.agentName = agentName;
        this.college = course;

    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
