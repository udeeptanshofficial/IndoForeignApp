package com.example.puff.finalproject.agent;

/**
 * Created by deeptansh on 26/4/17.
 */

public class UpdateModel {
    private String process;
    private String status;

    public UpdateModel(String process, String status) {
        this.process = process;
        this.status = status;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
