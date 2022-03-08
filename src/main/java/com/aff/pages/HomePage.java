package com.aff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aff.baseWeb.BasePage;
import com.aff.constants.applicationConstants;




public class HomePage extends BasePage{
	
	@FindBy(className = "topic-html-content-header")
	private WebElement headerText; 
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	 public static HomePage using(WebDriver driver) {
	        return new HomePage(driver);
	    }

	public String getHeaderText(){
		waitForElementToBeVisible(2,headerText);
		return headerText.getText();
	}

	@Override
	protected void setPageproperties() {
		this.pageTitle = applicationConstants.TITLE_HOME_PAGE;
		this.pageUrl = applicationConstants.URL_HOME_PAGE;
	}
}
