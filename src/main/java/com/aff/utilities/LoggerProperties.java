package com.aff.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerProperties {
	
	private Logger logger;
	private static LoggerProperties instance = null;
	
	public static LoggerProperties getInstance(){
		if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new LoggerProperties();
                }
            }
        }
        return instance;
	}
	
	private LoggerProperties(){
		logger = Logger.getLogger("devpinoyLogger");
        PropertyConfigurator.configure("log4j.properties");
		
	}

}
