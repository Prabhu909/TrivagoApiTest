package com.test.api.response;

public class ApiErrorResponse {

	public String code = null;
	public String message = null;
	
	public ApiErrorResponse() {
		
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		
		return "ClassPojo [code = "+code+", message = "+message+"]";
	}
	
}
