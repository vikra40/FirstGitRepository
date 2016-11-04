package com.capgemini.exceptions;

public class InsufficientBalanceException  extends Throwable {

	private String message;

	public InsufficientBalanceException(String message) {
		
		this.message = message;
	}
	
}
