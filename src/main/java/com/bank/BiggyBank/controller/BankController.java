package com.bank.BiggyBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BiggyBank.service.AccountService;

@RestController
public class BankController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/")
	public String home()
	{
		return " Hello ";
	}
	
	@PostMapping("/deposit/{amount}")
	public void deposit(@PathVariable long amount)
	{
		
	}

}
