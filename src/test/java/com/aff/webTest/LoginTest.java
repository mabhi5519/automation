package com.aff.webTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aff.pages.HomePage;
import com.aff.pages.LoginPage;
import com.aff.utilities.Utils;



public class LoginTest extends BaseTest {
	


	@BeforeTest(description = "Start browser")
	public void openStartURL() {
		driver.get(TestData.START_URL);	
	}
	

	@Test
	public void verifySuccessfulLogin(){
		//homePage = new HomePage(driver);
		Utils.pauseExecution(2000); 
		//Assert.assertTrue(HomePage.using(driver).isCurrentPage(), "home page is not the current Page");
		HomePage.using(driver).PageHeader().clickLogin();
		
		logger.error("Test log");
		Utils.pauseExecution(2000); 
		//Assert.assertTrue(LoginPage.using(driver).isCurrentPage(), "login page is not the current Page");
		LoginPage.using(driver).setEmail("email@gmail.com").setPassword("password").clickLogin();
	}
	
	
}
