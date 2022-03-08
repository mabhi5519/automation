package com.aff.exceptions;

public class environmentException extends RuntimeException {
	
	public  environmentException() {
        super();
    }

    public  environmentException(String message) {
        super(message);
    }

    public environmentException(Throwable exception) {
        super(exception);
    }

    public  environmentException(String message, Throwable exception) {
        super(message, exception);
    }

}
