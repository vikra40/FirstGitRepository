package com.capgemini.exceptions;

public class InvalidAccountNumberException extends Throwable {
	private String message;

	public InvalidAccountNumberException(String message) {
		
		this.message = message;
	}
	

}
