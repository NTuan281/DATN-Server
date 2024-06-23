package com.myproject.main.request;

import java.sql.Date;

public class SubmissionRequest {
    private int problemId;
    private Date submissionTime;

    // Default constructor
    public SubmissionRequest() {
    }

    // Constructor with parameters
    public SubmissionRequest(int problemId, Date submissionTime) {
        this.problemId = problemId;
        this.submissionTime = submissionTime;
    }

    // Getter and setter for problemId
    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    // Getter and setter for submissionTime
    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }
}
