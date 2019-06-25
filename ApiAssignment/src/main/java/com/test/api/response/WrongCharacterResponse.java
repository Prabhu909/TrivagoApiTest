package com.test.api.response;

public class WrongCharacterResponse {


	public String code = null;
	public String status = null;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		
		return "ClassPojo [code = "+code+", status = "+status+"]";
	}
	
	
}
