package com.swd.agri.service;

import com.swd.agri.model.Account;

public interface AccountService {
	
	public Integer register(Account account);
	
	public Account getAccountById(Integer accountId);

}
