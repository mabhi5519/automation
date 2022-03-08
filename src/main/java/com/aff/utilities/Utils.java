package com.aff.utilities;

public class Utils {
	private Utils(){
	}
	// TODO : to avoid using this function as much as possible. 
	public static void pauseExecution(int milliSeconds){
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
