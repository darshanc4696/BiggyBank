package com.bank.BiggyBank.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class BankUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String username;
	private String email;
	private String phoneNumber;
	private String address;
	
	public BankUsers(int userid, String username, String email, String phoneNumber, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public BankUsers(String username, String email, String phoneNumber, String address) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
