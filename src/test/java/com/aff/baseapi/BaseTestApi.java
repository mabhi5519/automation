package com.aff.baseapi;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.aff.constants.applicationConstants;
import com.aff.utilities.ApplicationProperties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;








public  class BaseTestApi {
	protected ApplicationProperties apiProperties;
	public static String baseurl;
	public static String uriPath;
	protected Response response; 
	Logger logger;
	
	public BaseTestApi(){
		logger = Logger.getLogger("BaseTestApi.class");
	}


	@BeforeClass
	public void setup(){
		System.out.println("in base tes setup");
		apiProperties = ApplicationProperties.getInstance("api");
		baseurl = apiProperties.getProperty(applicationConstants.REST_URI);
		uriPath = apiProperties.getProperty(applicationConstants.REST_ASSURED_USER_PATH);
		System.out.println("base url in setup is"+baseurl);
		System.out.println("uri in setup is"+uriPath);
		setBaseUri(baseurl);
		getURIPage(uriPath);
		
	}	
	
	public void setBaseUri(String uri) {
		RestAssured.baseURI = uri;
		logger.info("the base uri is set");
	}
	public void getURIPage(String uriParameter) {
		response=RestAssured.given().get("/"+uriParameter).andReturn();
		logger.info("Getting the response from page URI");		    
		
	}
	
	public void responseNotNull() {
		Assert.assertNotNull(response);
	}
	
	public void verifyResponeStatusCode(int statusCode) {
		Assert.assertEquals(response.getStatusCode(),statusCode);
	}
	
	public String getResponeBody() {
		return response.getBody().asString();
	}
	
	public void verifyResponseField(String field, String expectedValue) {
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get(field),expectedValue);
	}
}
