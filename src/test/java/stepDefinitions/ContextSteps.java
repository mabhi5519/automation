package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aff.constants.applicationConstants;
import com.aff.frameworkweb.DriverManager;
import com.aff.frameworkweb.DriverManagerFactory;
import com.aff.frameworkweb.DriverType;
import com.aff.utilities.ApplicationProperties;
import com.aff.webTest.TestData;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ContextSteps  {
	protected ApplicationProperties appPropertiesWeb,appPropertiesApi;
    private   WebDriver driver;
    protected DriverManager driverManager;
    protected DriverType driverType;
    
   
    public WebDriver getDriver() {
        return driver;
     }
    
    @Before(order=1)
	public void setup() {
    	appPropertiesWeb = appPropertiesWeb.getInstance("application");
    	appPropertiesApi = appPropertiesApi.getInstance("api");
		  setBrowserType("chrome");
			driverManager = DriverManagerFactory.getDriverManager(driverType);
			driver = driverManager.getDriver();	
			System.out.println("the driver instance in hooks is:"+driver);
			
	}

    @Before(order=2)
    public void configureWaitTimes() {
    	System.out.println("hit before of order 2");
        int implicitWaitTime = Integer.parseInt(appPropertiesWeb.getProperty(applicationConstants.IMPLICIT_WAIT_TIME));
        long pageloadWaitTime = Integer.parseInt(appPropertiesWeb.getProperty(applicationConstants.PAGE_LOAD_WAIT_TIME));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageloadWaitTime, TimeUnit.SECONDS);
    }

    @Before(order=3)
    public void maximizeBrowser() {
    	System.out.println("hit before of order 3");
        driver.manage().window().maximize();
        System.out.println("driver instance is :"+driver);
    }
    @Before(order=4)
    public void openStartURL() {
		driver.get(TestData.START_URL);	
	}

    @After
    public void stopBrowser() {
        driverManager.quit();
    }
     
    
    protected void setBrowserType(String browserString) {
     	if(browserString.equals(null)) {
     		browserString = appPropertiesWeb.getProperty(applicationConstants.BROWSER_NAME);
     	}
         if (browserString.equalsIgnoreCase("chrome")) {
             driverType = DriverType.CHROME;
         } else if (browserString.contains("firefox")) {
             driverType = DriverType.FIREFOX;
         } else if (browserString.equalsIgnoreCase("grid-chrome")) {
             driverType = DriverType.GRID_CHROME;
         } else {
             throw new AssertionError("Unsupported Browser");
         }
     }


}
