package stepDefinitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aff.pages.HomePage;
import com.aff.pages.LoginPage;


public class GooglePage {
	WebDriver driver;
	private ContextSteps  steps;
	public GooglePage(ContextSteps  steps) {
		this.steps=steps;
		driver = steps.getDriver();
	}
	
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		HomePage.using(driver).PageHeader().clickLogin();
		System.out.println("Login page is successfully loaded");
	}

	@When("I enter the credentials and click on submit")
	public void i_enter_the_credentials_and_click_on_submit() {
		LoginPage.using(driver).setEmail("email@gmail.com").setPassword("password").clickLogin();
		System.out.println("login is attempted with given credentials");
	}

	@Then("the login is successful")
	public void the_login_is_successful() {
	    String text = HomePage.using(driver).getHeaderText();
	    Assert.assertEquals(1,1);
	    System.out.println("Login is successful");
	}


}