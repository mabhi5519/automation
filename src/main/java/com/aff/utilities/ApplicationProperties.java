package com.aff.utilities;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	
	private Properties properties;
	private static ApplicationProperties apiInstance = null;
	private static ApplicationProperties webInstance = null;
	private static ApplicationProperties dbInstance = null;
	
	public static ApplicationProperties getInstance(String propFileName){
		if(propFileName.equals("db")) {
			synchronized (ApplicationProperties.class) {
                if (dbInstance == null) {
                    
                	dbInstance = new ApplicationProperties(propFileName);
                }
            }
			return dbInstance;
		}
		if(propFileName.equals("api")) {
			synchronized (ApplicationProperties.class) {
                if (apiInstance == null) {
                    
                	apiInstance = new ApplicationProperties(propFileName);
                }
            }
			return apiInstance;
		}else {
			synchronized (ApplicationProperties.class) {
                if (webInstance == null) {
                    
                	webInstance = new ApplicationProperties(propFileName);
                }
            }
			return webInstance;
			
		}
		
	
	}
	
	private ApplicationProperties(String propFile){
		String filePath = "src\\test\\resources\\properties\\"+propFile+".properties";
		FileReader reader;
		try {
			reader = new FileReader(filePath);
			properties = new Properties();
			properties.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	



	
	public String getProperty(String name){
		return properties.getProperty(name);
	}
}
