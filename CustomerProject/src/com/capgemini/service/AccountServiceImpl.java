package com.capgemini.service;

import com.capgemini.exceptions.DuplicateAccountNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepo;
	public AccountServiceImpl(AccountRepository accountRepo)
	{
		this.accountRepo=accountRepo;
	}
	
	@Override
	public Account createAccount(int accountNumber, int amount, Customer customer)
			throws InsufficientInitialBalanceException,DuplicateAccountNumberException, InvalidAccountNumberException {

		if(amount<500)
		{
			throw new InsufficientInitialBalanceException("Initial balance is low");
		}
		if(accountNumber==123456)
		{
			throw new DuplicateAccountNumberException("Account already exists");
		}
		if(accountNumber==000000||accountNumber==999999)
		{
			throw new InvalidAccountNumberException("Invalid Account");
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		account.setCustomer(customer);
		
		if(accountRepo.save(account))
		{
			return account;
		}
		
		
		return null;
	}

	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException {
		
		Account account=new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		if(amount<=0)
		{
			throw new InsufficientBalanceException("Amount Should not be equal to zero or negative");
		}
		if(accountNumber==000000||accountNumber==999999)
		{
			throw new InvalidAccountNumberException("Invalid Account");
		}
		if(accountRepo.updateAccount(account))
		{
			return account;
		}
		return null;
	}

	@Override
	public Account withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		Account account = accountRepo.searchAccount(accountNumber);
		System.out.println(account);
		int newAmount=0;
		if(account !=null){
			int acntAmount = account.getAmount();
			if(amount>acntAmount)
				throw new InsufficientBalanceException("Insufficient Balance!!!");
			else
				newAmount=acntAmount-amount;
		}
		
		//account.setAmount(newAmount);
		//accountRepo.updateAccount(account);
		return account;
	}

	@Override
	public String fundTransfer(int fromAccount, int toAccount, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		String status = null;
		Account accountFrom = accountRepo.searchAccount(fromAccount);
		Account accountTo = accountRepo.searchAccount(toAccount);
		if(accountFrom !=null && accountTo!=null)
		{
			int amountTo = accountTo.getAmount();
			amountTo = amountTo+amount;
			accountTo.setAmount(amountTo);
			accountRepo.updateAccount(accountTo);
			status = "Success";
		}
		else{
			status = "No Success";
		}
		return status;
	}

	@Override
	public int showBalance(int accountNumber, int amount) throws InvalidAccountNumberException {
		
		return 0;
	}

}
