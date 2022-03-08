package com.aff.baseWeb;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aff.pages.PageHeader;



public abstract class BasePage {

	protected WebDriver driver;
	protected PageHeader header;

	protected String pageTitle;
	protected String pageUrl;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		header = PageHeader.getInstance(this.driver);
		// defaults to title strategy if not set by setPageproperties

		setPageproperties();
	}

	protected abstract void setPageproperties();



	public String getTitle() {
		return driver.getTitle();
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public String getPageURL() {
		return this.pageUrl;
	}

	public PageHeader PageHeader() {
		return header;
	}

	protected boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}
	
	protected void waitForElementClickable(int timeout, final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(1, TimeUnit.SECONDS)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementToBeVisible(int timeout, final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(element));
	}

	
	
}
