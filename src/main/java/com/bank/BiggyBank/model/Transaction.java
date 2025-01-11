package com.bank.BiggyBank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	private String type;
	private LocalDateTime timeStamp;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;


	public Transaction() {
		super();
	}


	public Transaction(Long id, BigDecimal amount, String type, LocalDateTime timeStamp, Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.timeStamp = timeStamp;
		this.account = account;
	}


	public Transaction(BigDecimal amount, String type, LocalDateTime timeStamp, Account account) {
		super();
		this.amount = amount;
		this.type = type;
		this.timeStamp = timeStamp;
		this.account = account;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public String toString()
	{
		return id+", "+amount+", "+type+", "+timeStamp;
	}
}
