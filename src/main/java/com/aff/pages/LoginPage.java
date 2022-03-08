package com.aff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aff.apimodel.User;
import com.aff.baseWeb.BasePage;
import com.aff.constants.applicationConstants;

;


public class LoginPage extends BasePage{

	@FindBy(id = "Email")
	private WebElement email;
	
	@FindBy(id = "Password")
	private WebElement password;
	
	@FindBy(xpath = "//input[@type='submit'][@value='Log in']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public static LoginPage using(WebDriver driver) {
        return new LoginPage(driver);
    }
	
	public LoginPage setEmail(String email){
		this.email.sendKeys(email);
		return this;
	}
	
	public LoginPage setPassword(String password){
		this.password.sendKeys(password);
		return this;
	}
	
	public void clickLogin(){
		loginButton.click();

	}

	public void login(User user){
		//this.setEmail().clickLogin();

	}
	
	@Override
	protected void setPageproperties() {
		this.pageTitle = applicationConstants.TITLE_LOGIN_PAGE;
		this.pageUrl = applicationConstants.URL_LOGIN_PAGE;
	}
}
