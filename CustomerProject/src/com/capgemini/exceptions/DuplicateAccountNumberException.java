package com.capgemini.exceptions;

public class DuplicateAccountNumberException extends Throwable {

	private String message;

	public DuplicateAccountNumberException(String message) {
		
		this.message = message;
	}
	
}
