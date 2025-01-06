package com.bank.BiggyBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	@GetMapping("/")
	public String home()
	{
		return " Hello ";
	}

}
