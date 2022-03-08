package com.aff.restAssuredTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aff.apimodel.User;
import com.aff.baseapi.BaseTestApi;
import com.aff.webTest.TestData;



public class RestAssuredTests extends BaseTestApi {
	
	

	@Test(description = "verifies the response status code")
	public void verifyStatusCode() {
		int responseStatusCode = response.getStatusCode();
		Assert.assertEquals(responseStatusCode,TestData.STATUS_CODE_OK, "Expected response status code is :"
				+ TestData.STATUS_CODE_OK + " Actual status code is " + responseStatusCode);
	}

	@Test(description = "verifies the content-type header for response")
	public void verifyResponseHeader() {
		String valueOfContentHeader = response.getHeader(TestData.CONTENT_TYPE);
		Assert.assertEquals(TestData.EXPECTED_CONTENT_HEADER, valueOfContentHeader.trim(), "Expected content-type is "
				+ TestData.EXPECTED_CONTENT_HEADER + " Actual content-type is " + valueOfContentHeader);
	}

	@Test(description = "verifies the response body")
	public void verifyResponseBody() {
		User[] actualUsers = response.as(User[].class);
		int actualNumberOfUsers = actualUsers.length;
		Assert.assertEquals(TestData.EXPECTED_NUMBER_OF_USERS, actualNumberOfUsers,
				"Expected number of users in response is " + TestData.EXPECTED_NUMBER_OF_USERS
						+ " Actual number of users in response is " + actualNumberOfUsers);
	}
}
