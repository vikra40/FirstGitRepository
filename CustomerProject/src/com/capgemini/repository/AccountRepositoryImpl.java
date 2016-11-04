package com.capgemini.repository;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public class AccountRepositoryImpl implements AccountRepository{

	public List<Account> accounts = new ArrayList<>();
	@Override
	public boolean save(Account account) {
		Account accountDemo = new Account();
		Customer customerDemo = new Customer();
		customerDemo.setFirstName(account.getCustomer().getFirstName());
		customerDemo.setLastName(account.getCustomer().getLastName());
		customerDemo.setCity(account.getCustomer().getCity());
		accountDemo.setAccountNumber(account.getAccountNumber());
		accountDemo.setAmount(account.getAmount());
		accountDemo.setCustomer(customerDemo);
		accounts.add(accountDemo);
		System.out.println("searchAccount "+accounts);
		return true;
	}

	@Override
	public Account searchAccount(int accountNumber)  {
		System.out.println("searchAccount "+accounts);
		
		for(Account acnt : accounts){
			if(accountNumber==acnt.getAccountNumber())
			{
				return acnt;
			}
			else{
				try {
					throw new InvalidAccountNumberException("Invalid Account Number!!!");
				} catch (InvalidAccountNumberException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public boolean updateAccount(Account account) {
		Account accountForUpdate = searchAccount(account.getAccountNumber());
		if(accountForUpdate != null){
			accountForUpdate.setAccountNumber(account.getAccountNumber());
			accountForUpdate.setAmount(account.getAmount());
			//accountForUpdate.setCustomer(account.getCustomer());
		}
		return false;
	}

}
