package com.pk.exception;

public class RestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The Constant serialVersionUID. */

	/** The message. */
	private String message;

	/**
	 * Instantiates a new rest exception.
	 *
	 * @param message
	 *            the message
	 */
	public RestException(String message) {
		super(message);
		this.message = message;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return "RestException [ message=" + message + "]";
	}

}
