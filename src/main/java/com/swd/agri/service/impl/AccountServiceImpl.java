package com.swd.agri.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.dao.AccountDao;
import com.swd.agri.model.Account;
import com.swd.agri.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public Integer register(Account account) {

		Integer accoutId = accountDao.register(account);
		
		return accoutId;
		
	}

	@Override
	public Account getAccountById(Integer accountId) {
		
		Account account = accountDao.getAccountById(accountId);
		
		return account;
		
	}

}
