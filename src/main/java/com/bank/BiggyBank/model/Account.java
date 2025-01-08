package com.bank.BiggyBank.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Component
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	private BigDecimal balance;
	
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	public Account() {
		super();
	}

	public Account(int id, String name, String password, BigDecimal balance, List<Transaction> transactions,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.transactions = transactions;
		this.authorities = authorities;
	}
	
	public Account(String name, String password, BigDecimal balance, List<Transaction> transactions,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.transactions = transactions;
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}