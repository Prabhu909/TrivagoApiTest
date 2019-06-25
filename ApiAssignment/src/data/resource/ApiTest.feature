Feature: API test

@testOne_apiKeyMissing
Scenario: Error code validation Test
    Given i start validating "ERROR" Scenario
    Then i validate the missing "API" Key error
   
@testTwo_hashKeyMissing
Scenario: Error code validation Test for hashkey
    Given i start validating "ERROR" Scenario
    Then i validate the missing "HASHKEY" Key error
    
@testThree_timeStampMissing    
Scenario: Error validation for timeStamp missing
	Given i start validating "ERROR" Scenario
    Then i validate the missing "TIMESTAMP" Key error
    
@testFour_invalidHashError
Scenario: Error validation for invalidHash error
	Given i start validating "ERROR" Scenario
    Then i validate the missing "INVALIDHASH" Key error    
    
@testFour_unknownApiKey
Scenario: Error validation for unknown Api key
	Given i start validating "ERROR" Scenario
   Then i validate the missing "UNKNOWN_APIKEY" Key error      

@testFive_methodNotAllowed
Scenario: Error validation for wrong Method
	Given i start validating "ERROR" Scenario
    Then i validate the missing "Method_Not_allowed" Key error
    
@testSix_comicsValidationTest
Scenario: Error validation for comics details
	Given i start validating "COMICS" Scenario
    Then i validate the comics details    
    
@testSeven_Marvel_Character_Validation
Scenario: Error validation for SpiderMan details validation
	Given i start validating "Marvel_Characters" Scenario
    Then i validate marvel character scenario for "positive" cases
    Then i validate marvel character scenario for "negative" cases
    
