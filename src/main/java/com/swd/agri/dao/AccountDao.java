package com.swd.agri.dao;

import com.swd.agri.model.Account;

public interface AccountDao {
	
	public Integer register(Account account);
	
	public Account getAccountById(Integer accountId);

}
