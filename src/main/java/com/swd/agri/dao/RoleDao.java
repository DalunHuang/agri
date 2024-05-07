package com.swd.agri.dao;

import java.util.List;

import com.swd.agri.model.Role;

public interface RoleDao {
	
	public List<Role> getRoles(Integer accountId);
	
	public List<Role> getRoles(String username);
	
}
