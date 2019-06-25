package com.api.test.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.test.actions.ApiActions;
import com.test.api.response.ApiErrorResponse;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ApiSteps {

public static Logger LOGGER = LoggerFactory.getLogger("static Logger");
	
	ApiActions apiActions = new ApiActions(); 
	ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
	
	@Given("^i start validating \\\"([^\\\"]*)\\\" Scenario$")
	public void i_start_validating_Error_Scenario(String scenarioType) throws Exception {
		
		if(scenarioType.equalsIgnoreCase("ERROR")) {
			
		System.out.println("i start validating Error Scenario");
		}
		else if(scenarioType.equalsIgnoreCase("COMICS")) {
			
			LOGGER.info("COMICS scenarios");
		}
		else if(scenarioType.equalsIgnoreCase("Marvel_Characters")) {
			
			LOGGER.info("Marvel_Characters scenarios");
		}
		
	}

	@Then("^i validate the missing \"([^\"]*)\" Key error$")
	public void i_validate_the_missing_Api_Key_error(String missingValue) throws Exception {
		
		if(missingValue.equalsIgnoreCase("API")) {
			
		System.out.println("API-Key Error validation");
		apiActions.validateErrorMessageForMissingAPIKey();
		}
		
		else if(missingValue.equalsIgnoreCase("HASHKEY")) {
			
			LOGGER.info("HashKey Error Validation");
			apiActions.validateErrorMessageForMissingHashKey();
		}
		else if(missingValue.equalsIgnoreCase("TIMESTAMP")) {
			
			LOGGER.info("TimeStamp Error Validation");
			apiActions.validateErrorMessageForMissingTimeStampKey();
		}
		else if(missingValue.equalsIgnoreCase("INVALIDHASH")) {
			
			LOGGER.info("INVALIDHASH Validation");
			apiActions.validateErrorMessageForInvalidHashKey();
		}
		else if(missingValue.equalsIgnoreCase("UNKNOWN_APIKEY")) {
			
			LOGGER.info("UNKNOWN_APIKEY Validation");
			apiActions.validateErrorMessageForunknownApihKey();
		}
		else if(missingValue.equalsIgnoreCase("Method_Not_allowed")) {
			
			LOGGER.info("Method_Not_allowed Validation");
			apiActions.validateErrorMessageForMethodNotAllowed();
		}

		 
	}
	
			 
	@Then("^i validate the comics details$")
	public void i_validate_the_comics_details() throws Exception {
		
		LOGGER.info("Comics Validation");
		apiActions.comicsValidation();
		
	}
	
	@Then("^i validate marvel character scenario for \"([^\"]*)\" cases$")
	public void i_validate_marvelCharacter_scenarrios(String caseType) throws Exception {
		
		if(caseType.equalsIgnoreCase("positive")) {
			
			if(caseType.equalsIgnoreCase("positive")) {
				
			
			LOGGER.info("Start validation for positive scenario");
			apiActions.marvel_character_positive_cases();
			}
			else if(caseType.equalsIgnoreCase("negative")) {
				
				System.out.println("Start validation for negative scenario");
				apiActions.marvel_character_negative_cases();	
			}
			
		}
		
		
	}
	
	
	
}
