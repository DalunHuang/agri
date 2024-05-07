package com.swd.agri.service;

import java.util.List;

import com.swd.agri.model.Role;

public interface RoleService {

	public List<Role> getRoles(Integer accountId);
	
	public List<Role> getRoles(String username);
	
}
