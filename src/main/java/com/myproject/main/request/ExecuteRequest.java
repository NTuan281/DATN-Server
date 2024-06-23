package com.myproject.main.request;

import java.util.List;

public class ExecuteRequest {
	private String code;
	private List<Object> parameters;
	private String returnType;
	private String output;
	private String functionName;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Object> getParameters() {
		return parameters;
	}
	public void setParameters(List<Object> parameter) {
		this.parameters = parameter;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public ExecuteRequest(String code, List<Object> parameters,String returnType, String output, String functionName) {
		super();
		this.code = code;
		this.parameters = parameters;
		this.output = output;
		this.functionName = functionName;
		this.returnType = returnType;
	}
	public ExecuteRequest() {
		super();
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	
}