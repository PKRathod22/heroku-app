package com.pk.exception;

public class RestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public RestException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "RestException [ message=" + message + "]";
	}

}
