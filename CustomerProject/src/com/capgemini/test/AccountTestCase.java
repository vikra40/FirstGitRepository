package com.capgemini.test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.DuplicateAccountNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

/**
 * @author vikrverm
 *
 */
public class AccountTestCase {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService =new AccountServiceImpl(accountRepository);
	}


	/**createAccount
	 * 1. when the valid info is passed account should be created successfully
	 * 2. when the amount is less than 500 system should throw exception
	 * 3. when the account is already exists system should throw exception
	 * 4. when the invalid info is passed system should throw exception
	 * @throws InvalidAccountNumberException 
	 * 
	 */

	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException
	{
		Customer customer = new Customer();
		customer.setFirstName("Pooja");
		customer.setLastName("Singh");
		customer.setCity("Pune");
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		account.setCustomer(customer);
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000, customer));
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException
	{
		Customer customer = new Customer();
		customer.setFirstName("Pooja");
		customer.setLastName("Singh");
		customer.setCity("Pune");
		accountService.createAccount(101, 400, customer);
	}
	
	@Test(expected=com.capgemini.exceptions.DuplicateAccountNumberException.class)
	public void WhenTheAccountIsAlreadyExistsSystemShouldThrowException() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException
	{
		Customer customer = new Customer();
		customer.setFirstName("Pooja");
		customer.setLastName("Singh");
		customer.setCity("Pune");
		accountService.createAccount(123456, 3400, customer);
	}
	
	@Test(expected=com.capgemini.exceptions.DuplicateAccountNumberException.class)
	public void WhenTheInvalidInfoIsPassedSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientInitialBalanceException, DuplicateAccountNumberException
	{
		
		Customer customer = new Customer();
		customer.setFirstName("Pooja");
		customer.setLastName("Singh");
		customer.setCity("Pune");
		accountService.createAccount(000000, 3400, customer);
	}
	
	/**depositAmount
	 * 1. when the valid info is passed amount should be deposited successfully
	 * 2. when the amount is negative system should throw exception
	 * 3. when the invalid info is passed system should throw exception
	 * @throws DuplicateAccountNumberException 
	 * @throws InsufficientInitialBalanceException 
	 * @throws InsufficientBalanceException 
	 * 
	 */
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDepositedSuccessfully() throws InvalidAccountNumberException, InsufficientInitialBalanceException, DuplicateAccountNumberException, InsufficientBalanceException
	{

		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.updateAccount(account)).thenReturn(true);
		
		assertEquals(account, accountService.depositAmount(101, 5000));
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheAmountIsNegativeSystemShouldThrowException() throws InsufficientBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException
	{
	
		accountService.depositAmount(101, -3);
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void WhenTheInvalidInfoIsPassedSystemShouldThrowExceptionDeposit() throws InvalidAccountNumberException, InsufficientInitialBalanceException, DuplicateAccountNumberException, InsufficientBalanceException
	{
		accountService.depositAmount(000000, 3000);
	}
}
