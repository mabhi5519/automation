package com.aff.frameworkweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager {

	protected RemoteWebDriver instance;

	// factory method to create driver instance
	protected abstract void createDriver();

	public RemoteWebDriver getDriver() {
		if (instance == null) {
			synchronized (DriverManager.class) {
				if (instance == null) {
					createDriver();
				}
			}
		}
		return instance;
	}

	public void quit() {
		if (instance != null) {
			instance.quit();
			instance = null;
		}
	}
}
