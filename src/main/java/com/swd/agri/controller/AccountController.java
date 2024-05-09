package com.swd.agri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swd.agri.model.Account;
import com.swd.agri.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/register")
	public Account register(@RequestBody Account account) {
		
		String passwordHashed = encoder.encode(account.getPassword());
		account.setPassword(passwordHashed);
		
		Integer accountId = accountService.register(account);
		
		return accountService.getAccountById(accountId);
		
	}
	
}
