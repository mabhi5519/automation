package com.aff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aff.utilities.ApplicationProperties;



public class PageHeader {

	private static PageHeader instance = null;
	private WebDriver driver = null;
	
	private By loginLinkLocator = By.linkText("Log in");
	
	public static PageHeader getInstance(WebDriver driver){
		if (instance == null) {
            synchronized (ApplicationProperties.class) {
                if (instance == null) {
                    instance = new PageHeader(driver);
                }
            }
        } else {
        	instance.driver = driver;
        }
		
		return instance;
	}
	
	private PageHeader(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage clickLogin(){
		WebElement loginLink = driver.findElement(loginLinkLocator);
		loginLink.click();
		return new LoginPage(driver);
	}
	
}
