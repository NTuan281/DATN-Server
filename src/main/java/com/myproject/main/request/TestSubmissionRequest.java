package com.myproject.main.request;

public class TestSubmissionRequest {
	private int problemId;

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public TestSubmissionRequest(int problemId) {
		super();
		this.problemId = problemId;
	}

	public TestSubmissionRequest() {
		super();
	}
	
}
