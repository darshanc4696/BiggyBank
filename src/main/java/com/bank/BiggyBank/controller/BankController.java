package com.bank.BiggyBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.BiggyBank.service.AccountService;

@Controller
public class BankController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@PostMapping("/deposit/{amount}")
	public void deposit(@PathVariable long amount)
	{
		
	}

}
