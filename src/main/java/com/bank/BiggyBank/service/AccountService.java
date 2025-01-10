package com.bank.BiggyBank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.BiggyBank.model.Account;
import com.bank.BiggyBank.model.Transaction;
import com.bank.BiggyBank.repo.AccountRepo;
import com.bank.BiggyBank.repo.TransactionRepo;

@Service
public class AccountService implements UserDetailsService  {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	public Account findAccountByUsername(String username)
	{
		return accountRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
	}
	
	public Account registerAccount(String name, String password)
	{
		if(accountRepo.findByUsername(name).isPresent())
		{
			throw new RuntimeException("Username Already exists...");
		}
		
		Account ac = new Account();
		ac.setUsername(name);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = findAccountByUsername(username);
		
		if(account == null)
		{
			throw new UsernameNotFoundException("Username or Password not found");
		}
		
		return new Account(
				account.getUsername(),
				account.getPassword(),
				account.getBalance(),
				account.getTransactions(),
				authorities()
				);
	}
	
	
	public Collection<? extends GrantedAuthority> authorities()
	{
		return Arrays.asList(new SimpleGrantedAuthority("User"));
	}
	
	public void transferAmount(Account account, String toUsername, BigDecimal amount)
	{
		Account toAccount = findAccountByUsername(toUsername);
		
		if(toAccount == null)
		{
			throw new RuntimeException("Recepient Account not found");
		}
		
		if(account.getBalance().compareTo(amount) < 0)
		{
			throw new RuntimeException("Insufficient amount");
		}
		
		// Deduct
		account.setBalance(account.getBalance().subtract(amount));
		accountRepo.save(account);
		
		Transaction transaction1 = new Transaction(amount, "Transfered to "+ toUsername, LocalDateTime.now(), account);
		transactionRepo.save(transaction1);
		
		
		// Add
		toAccount.setBalance(toAccount.getBalance().add(amount));
		accountRepo.save(toAccount);
		
		Transaction transaction2 = new Transaction(amount, "Received from "+ account.getUsername(), LocalDateTime.now(), toAccount);
		transactionRepo.save(transaction2);

	}
	
	
	

	

}
