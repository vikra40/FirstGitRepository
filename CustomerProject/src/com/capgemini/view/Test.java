package com.capgemini.view;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Customer;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import com.capgemini.service.CustomerService;
import com.capgemini.service.CustomerServiceImpl;

public class Test {

	public static void main(String[] args) {

		CustomerService cc = new CustomerServiceImpl();
		
		//System.out.println(cc.findAll().get(0).getFirstName() +" "+cc.findAll().get(0).getLastName());
		
		AccountService ac = new AccountServiceImpl(null);
		/*Customer c = new Customer();
		c.setFirstName("Pooja");
		c.setLastName("Singh");
		c.setCity("Pune");*/
/*		try {
			System.out.println(ac.createAccount(123456, 20000, c));
			//System.out.println(ac.);
		} catch (InsufficientInitialBalanceException e) {
			
			e.printStackTrace();
		}*/
		
		try {
			System.out.println(ac.withdrawAmount(123456, 500000));
		} catch (InvalidAccountNumberException | InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
