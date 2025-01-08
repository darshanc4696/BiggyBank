package com.bank.BiggyBank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.BiggyBank.model.Account;
import com.bank.BiggyBank.model.Transaction;
import com.bank.BiggyBank.repo.AccountRepo;
import com.bank.BiggyBank.repo.TransactionRepo;

@Service
public class AccountService  {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	public Account findAccountByUsername(String username)
	{
		return accountRepo.findByName(username)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
	}
	
	public Account registerAccount(String name, String password)
	{
		if(accountRepo.findByName(name).isPresent())
		{
			throw new RuntimeException("Username Already exists...");
		}
		
		Account ac = new Account();
		ac.setName(name);
		ac.setPassword(passwordEncoder.encode(password));
		ac.setBalance(BigDecimal.ZERO);
		
		return accountRepo.save(ac);
		
	}
	
	public void deposit(Account account, BigDecimal amount)
	{
		account.setBalance(account.getBalance().add(amount));
		accountRepo.save(account);
		
		Transaction transaction = new Transaction(amount, "Deposit", LocalDateTime.now(), account);
		transactionRepo.save(transaction);
	}
	
	public void withdraw(Account account, BigDecimal amount)
	{
		if(account.getBalance().compareTo(amount) < 0)
		{
			throw new RuntimeException("Insufficient amount");
		}
		
		account.setBalance(account.getBalance().subtract(amount));
		accountRepo.save(account);
		
		Transaction transaction = new Transaction(amount, "Withdraw", LocalDateTime.now(), account);
		transactionRepo.save(transaction);
		
	}
	
	public List<Transaction> getAllTransactions(Account account)
	{
		return transactionRepo.findByAccountId(account.getId());
	}
	
	
	
	
	

	

}
