Feature: Validating place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
 Given Add Place Payload with "<name>" "<address>" "<language>"
 When user calls "AddPlaceAPI" with "POST" http request
 Then the API call is success with status code 200
 And "status" in response body is "OK"
 And "scope" in response body is "APP"
 And verify place_Id created maps to "<name>" using "getPlaceAPI"
 
 Examples:
    |name |address   |language |
    |mad  |1 street  |Kannada   |
    #|rav  |2 street  |english  |
 
@DeletePlace @Regression 
Scenario: Verify if Delete Place functionality  is working 
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
