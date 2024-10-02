package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils{
	
	ResponseSpecification res;
	RequestSpecification res1;
	Response res2;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	   
		//passing body as java object
		//Spec builder request and response
		 res1= given().spec(requestSpecification()).body(data.addPlacePayload(name,address,language));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//get resource
		//constructor will be called with value of resource which  you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
	      res2=res1.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			res2=res1.when().get(resourceAPI.getResource());
		
	           
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(res2.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String Exceptedvalue) {
		
		assertEquals(getJsonPath(res2,KeyValue),Exceptedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String exceptedname, String resource) throws IOException {
	    // request spec
	 place_id=getJsonPath(res2,"place_id");
		System.out.println(place_id);
		res1=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		
//		String actualname=getJsonPath(res2,"name");
//		assertEquals(actualname,exceptedname);
	
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    res1=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
