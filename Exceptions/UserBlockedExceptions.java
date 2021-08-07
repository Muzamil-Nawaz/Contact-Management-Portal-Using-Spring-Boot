package com.contact.Exceptions;

public class UserBlockedExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserBlockedExceptions() {

	}

	public UserBlockedExceptions(String errorDesc) {
		super(errorDesc);
	}
}
