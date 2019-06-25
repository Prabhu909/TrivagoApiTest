package com.api.test.interfaces;

import static com.jayway.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;

public class TestBase {

public static Logger LOGGER = LoggerFactory.getLogger("Static Logger");
	
	public static Response getApiResponse(int responseCode, String url) {
		
		Response response = given().contentType("application/json").and().get(url);
		int statusCode = response.getStatusCode();
		if(statusCode != responseCode) {
			
			LOGGER.info("Hmm Something went wrong :(" +statusCode);
			return null;
		}
		return response;
	}
	
	public static Response postApiResponse(int responseCode, String url) {
		
		Response response = given().contentType("application/json").and().post(url);
		int statusCode = response.getStatusCode();
		if(statusCode != responseCode) {
			
			LOGGER.info("Hmm Something went wrong :(" +statusCode);
			return null;
		}
		return response;
	}
	
}
