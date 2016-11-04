package com.capgemini.service;

import com.capgemini.exceptions.DuplicateAccountNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface AccountService {

	Account createAccount(int accountNumber, int amount, Customer customer) throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException;

	Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException;

	Account withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException;

	String fundTransfer(int fromAccount,int toAccount,int amount) throws InvalidAccountNumberException, InsufficientBalanceException;

	int showBalance(int accountNumber,int amount) throws InvalidAccountNumberException;
}
