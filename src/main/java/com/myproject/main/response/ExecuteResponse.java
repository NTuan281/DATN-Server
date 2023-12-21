package com.myproject.main.response;

public class ExecuteResponse {
	private String responseString;
	private boolean responseResult;
	
	public String getResponseString() {
		return responseString;
	}
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	public boolean isResponseResult() {
		return responseResult;
	}
	public void setResponseResult(boolean responseResult) {
		this.responseResult = responseResult;
	}
	public ExecuteResponse(String responseString, boolean responseResult) {
		super();
		this.responseString = responseString;
		this.responseResult = responseResult;
	}
	public ExecuteResponse() {
		super();
	}
	
	
}
