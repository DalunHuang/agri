package com.swd.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.dao.RoleDao;
import com.swd.agri.model.Role;
import com.swd.agri.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoles(Integer accountId) {
		
		List<Role> roles = roleDao.getRoles(accountId);
		
		return roles;
		
	}

	@Override
	public List<Role> getRoles(String username) {
		
		List<Role> roles = roleDao.getRoles(username);
		
		return roles;
		
	}

}
