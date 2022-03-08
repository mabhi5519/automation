package com.aff.exceptions;

public class httpRequestException extends RuntimeException{
	
	   public httpRequestException() {
	        super();
	    }

	    public httpRequestException(String message) {
	        super(message);
	    }

	    public httpRequestException(String message, Throwable exception) {
	        super(message, exception);
	    }

}
