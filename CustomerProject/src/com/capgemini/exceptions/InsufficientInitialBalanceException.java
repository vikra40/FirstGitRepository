package com.capgemini.exceptions;

public class InsufficientInitialBalanceException extends Throwable {
	

	private String message;
	public InsufficientInitialBalanceException(String message){
		this.message = message;
		
	}
}
