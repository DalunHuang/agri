package com.swd.agri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swd.agri.model.Account;
import com.swd.agri.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public Account register(@RequestBody Account account) {
		
		Integer accountId = accountService.register(account);
		
		return accountService.getAccountById(accountId);
		
	}
	
}
