package com.aff.webTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aff.constants.applicationConstants;
import com.aff.frameworkweb.DriverManager;
import com.aff.frameworkweb.DriverManagerFactory;
import com.aff.frameworkweb.DriverType;
import com.aff.utilities.ApplicationProperties;




public abstract class BaseTest {
    protected ApplicationProperties appProperties;
    protected WebDriver driver;
    private DriverManager driverManager;
    private DriverType driverType;
    final Logger logger;



    public BaseTest() {
        
       // logger = LoggerProperties.getInstance();
        logger = Logger.getLogger("devpinoyLogger");
     //  PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\Log4j.properties");
       appProperties = appProperties.getInstance("application");
    }
    
   

    @BeforeTest(description = "set up the environment")  
    @Parameters( {"browser"} )
    public void setup() {
    	//appProperties = ApplicationProperties.getInstance(environment);
    	//browser = System.getProperty("browser");
    	
    			setBrowserType("chrome");
    			driverManager = DriverManagerFactory.getDriverManager(driverType);
    			driver = driverManager.getDriver();		
    	}
    		
    
    
    			
    
    

    @BeforeTest(dependsOnMethods = "setup", description = "Adds implicit wait")
    public void configureWaitTimes() {
        int implicitWaitTime = Integer.parseInt(appProperties.getProperty(applicationConstants.IMPLICIT_WAIT_TIME));
        long pageloadWaitTime = Integer.parseInt(appProperties.getProperty(applicationConstants.PAGE_LOAD_WAIT_TIME));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageloadWaitTime, TimeUnit.SECONDS);
    }

    @BeforeTest(dependsOnMethods = "setup", description = "Maximizes the browser")
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    @AfterTest(description = "Stop Browser")
    public void stopBrowser() {
        driverManager.quit();
    }

    protected void setBrowserType(String browserString) {
    	if(browserString.equals(null)) {
    		browserString = appProperties.getProperty(applicationConstants.BROWSER_NAME);
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
