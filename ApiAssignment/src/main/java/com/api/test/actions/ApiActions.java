package com.api.test.actions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.test.interfaces.BaseConfig;
import com.api.test.interfaces.TestBase;
import com.api.test.responseCodes.ResponseCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.api.response.ApiErrorResponse;
import com.test.api.response.WrongCharacterResponse;

import junit.framework.Assert;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiActions extends TestBase{
	
	
	
	Retrofit retrofit;
	String url = BaseConfig.marvel_baseURL;//"https://gateway.marvel.com/";
	int comicsId = BaseConfig.comics_id;
	int character_id = BaseConfig.character_id;
	int wrong_char_id_test = BaseConfig.wrong_character_id;
	String hashKey_Value = BaseConfig.hashKey;
	String apiKey = BaseConfig.apiKey;
	String timeStamp = BaseConfig.timeStamp;
	String invalid_hashLey_Value = BaseConfig.invalid_hashKey;
	String unknown_apiKeyValue = BaseConfig.unknown_apiKey;
	String wrong_hashValue = BaseConfig.wrong_hash;
	
	ResponseCodes responseCodes;
	ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
	WrongCharacterResponse wrongCharacterResponse = new WrongCharacterResponse();
	private static String code;
	
	public static Logger LOGGER = LoggerFactory.getLogger("static Logger");
	
	
	
	
	public ApiErrorResponse validateErrorMessageForMissingAPIKey() throws Exception {
		
		/*System.out.println("---------- API call started ------------");
		Call<ApiErrorResponse> getMissingApiKeyError = apiInterface.verifyErrorResponse409MissingParameter("application/json");
		Response<ApiErrorResponse> response = getMissingApiKeyError.execute();
		System.out.println("&&&&&&&&&&&&&&&" +response.raw());
		System.out.println("Body ---" +response.body());
		Assert.assertEquals(responseCodes.CONFLICT, response.code());
		System.out.println("jkfbfkbjefbje" +response.message());
		return response.body();*/
		
		String urlTotTest = url+"v1/public/series?ts="+timeStamp+"&hash="+hashKey_Value+"";
		
		com.jayway.restassured.response.Response resp = getApiResponse(409, urlTotTest);
		LOGGER.info("Response Of API Key Missing -----" +resp.prettyPrint());
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.CONFLICT,resultObject);
		Assert.assertEquals("MissingParameter", resultData.getCode());
		Assert.assertEquals("You must provide a user key.", resultData.getMessage());
		
	return resultData ;
	}
	
	public ApiErrorResponse validateErrorMessageForMissingHashKey() throws Exception {

		String urlTotTest = url+"v1/public/series?ts="+timeStamp+"&apikey="+apiKey+"";
		
		com.jayway.restassured.response.Response resp = getApiResponse(409, urlTotTest);
		LOGGER.info("Response Of HashKey Missing -----" +resp.prettyPrint());
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.CONFLICT,resultObject);
		Assert.assertEquals("MissingParameter", resultData.getCode());
		Assert.assertEquals("You must provide a hash.", resultData.getMessage());
		
		return resultData ;
	}
	
	public ApiErrorResponse validateErrorMessageForMissingTimeStampKey() throws Exception {

		String urlTotTest = url+"v1/public/series?apikey="+apiKey+"&hash="+hashKey_Value+"";
		
		com.jayway.restassured.response.Response resp = getApiResponse(409, urlTotTest);
		LOGGER.info("Response Of TimeStamp Missing -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.CONFLICT,resultObject);
		Assert.assertEquals("MissingParameter", resultData.getCode());
		Assert.assertEquals("You must provide a timestamp.", resultData.getMessage());
		
		return resultData ;
	}
	
	public ApiErrorResponse validateErrorMessageForInvalidHashKey() throws Exception {

		String urlTotTest = url+"v1/public/series?ts="+timeStamp+"&apikey="+apiKey+"&hash="+invalid_hashLey_Value+"";
		
		com.jayway.restassured.response.Response resp = getApiResponse(401, urlTotTest);
		LOGGER.info("Response Of invalid HashKey  -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.UNAUTHORIZED,resultObject);
		Assert.assertEquals("InvalidCredentials", resultData.getCode());
		Assert.assertEquals("That hash, timestamp and key combination is invalid.", resultData.getMessage());
		
		return resultData ;
	}
	public ApiErrorResponse validateErrorMessageForunknownApihKey() throws Exception {

		String urlTotTest = url+"v1/public/series?ts=karma&apikey="+unknown_apiKeyValue+"&hash="+wrong_hashValue+"";
		
		System.out.println("Unknown ---------" +urlTotTest);
		
		com.jayway.restassured.response.Response resp = getApiResponse(401, urlTotTest);
		LOGGER.info("Response Of invalid HashKey  -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.UNAUTHORIZED,resultObject);
		Assert.assertEquals("InvalidCredentials", resultData.getCode());
		Assert.assertEquals("This user is not allowed to use that service.", resultData.getMessage());
		
		return resultData ;
	}
	
	public ApiErrorResponse validateErrorMessageForMethodNotAllowed() throws Exception {

		String urlTotTest = url+"v1/public/series?ts="+timeStamp+"&apikey="+apiKey+"&hash="+hashKey_Value+"";
		
		
		com.jayway.restassured.response.Response resp = postApiResponse(405, urlTotTest);
		LOGGER.info("Response Of invalid HashKey  -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		ApiErrorResponse resultData = body.as(ApiErrorResponse.class);
		int resultObject = resp.statusCode();
		Assert.assertEquals(responseCodes.METHOD_NOT_ALLOWED,resultObject);
		Assert.assertEquals("MethodNotAllowedError", resultData.getCode());
		Assert.assertEquals("POST is not allowed", resultData.getMessage());
		
		return resultData ;
	}
	
	public void comicsValidation() throws Exception {
		
		String urlTotTest = url+"v1/public/comics/"+comicsId+"?ts="+timeStamp+"&apikey="+apiKey+"&hash="+hashKey_Value+"";
		System.out.println("^^^^^^^^^^^" +urlTotTest);
		com.jayway.restassured.response.Response resp = getApiResponse(200, urlTotTest);
		LOGGER.info("Response Of invalid HashKey  -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		int responseCode = resp.getStatusCode();
		
		Assert.assertEquals(responseCodes.OK, responseCode);
		Assert.assertTrue("issue_solicit_text", true);
	}
	
	public void marvel_character_positive_cases() throws Exception {
		
		
		String urlTotTest = url+"v1/public/characters/"+character_id+"?ts="+timeStamp+"&apikey="+apiKey+"&hash="+hashKey_Value+"";
		
		System.out.println("^^^^^^^^^^^" +urlTotTest);
		com.jayway.restassured.response.Response resp = getApiResponse(200, urlTotTest);
		LOGGER.info("Response Of invalid HashKey  -----" +resp.prettyPrint());
		
		com.jayway.restassured.response.ResponseBody body =  resp.getBody();
		int responseCode = resp.getStatusCode();
		
		Assert.assertEquals(responseCodes.OK, responseCode);
		
		
		
		String responseStringType = resp.asString();
		
		JSONObject jsonObject = new JSONObject(responseStringType);
		JSONObject scope = (JSONObject) jsonObject.get("data");
		JSONArray jsonArray = (JSONArray) scope.get("results");
		
		JSONObject jsObject = jsonArray.getJSONObject(0);
		Object id = jsObject.get("id");
		Object name = jsObject.get("name");
		Object datemodified = jsObject.get("modified");
		Object thumbnail = jsObject.get("thumbnail");
			
		String idValue = id.toString();
		String marvel_character_name_value = name.toString();
		String date_modified = datemodified.toString();
		String thumbnailValue = thumbnail.toString();
		
		JSONObject jsArray = new JSONObject(thumbnailValue);
		System.out.println("JJJJJJJJJJJ ===" +jsArray.getString("path"));
		
		String path_Value = jsArray.getString("path");
		
		System.out.println("$$$$$$$$$$$$$$" +idValue);
		System.out.println("name is " +marvel_character_name_value);
		Assert.assertEquals(BaseConfig.marvel_character_name, marvel_character_name_value);
		Assert.assertEquals(BaseConfig.thumbnail_url, path_Value);
		System.out.println("$$$$$$$$$$$$$$" +datemodified);
		
	}
	
	public WrongCharacterResponse marvel_character_negative_cases() throws Exception {
		
		String urlTotTest = url+"v1/public/characters/"+wrong_char_id_test+"?ts="+timeStamp+"&apikey="+apiKey+"&hash="+hashKey_Value+"";
		
		System.out.println("^^^^^^^^^^^" +urlTotTest);
		com.jayway.restassured.response.Response resp = getApiResponse(404, urlTotTest);
		
		System.out.println("Response Of invalid HashKey  -----" +resp.prettyPrint());
		Assert.assertEquals("404", wrongCharacterResponse.getCode());
		Assert.assertEquals("We couldn't find that character", wrongCharacterResponse.getStatus());
		
		return wrongCharacterResponse;
	}
	
	
	

}
