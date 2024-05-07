package com.swd.agri.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swd.agri.dao.RoleDao;
import com.swd.agri.model.Role;

@SpringBootTest
public class RoleDaoImplTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void getRolesWithId() {
		
		//test account : peter
		int accountId = 1;
		String roleName = "ROLE_ADMIN";
		
		List<Role> roles = roleDao.getRoles(accountId);
		
		assertNotNull(roles);
		
		Optional<String> result = roles.stream()
				.filter(role -> role.getRoleName().equals(roleName))
				.map(Role::getRoleName)
				.findFirst();
		
		assertTrue(result.isPresent());
		assertEquals(roleName, result.get());
		
	}
	
	@Test
	public void getRoleWithUserName() {
		
		String accountName = "Peter";
		String roleName = "ROLE_ADMIN";
		
		List<Role> roles = roleDao.getRoles(accountName);
		
		assertNotNull(roles);
		
		Optional<String> result = roles.stream()
				.filter(role -> role.getRoleName().equals(roleName))
				.map(Role::getRoleName)
				.findFirst();
		
		assertTrue(result.isPresent());
		assertEquals(roleName, result.get());
		
	}
	
	@Test
	public void getRolesWithNoAdmin() {
		
		//test account : peter
		String accountName = "Sirius";
		String roleName = "ROLE_ADMIN";
		
		List<Role> roles = roleDao.getRoles(accountName);
		
		assertNotNull(roles);
		
		Optional<String> result = roles.stream()
				.filter(role -> role.getRoleName().equals(roleName))
				.map(Role::getRoleName)
				.findFirst();
		
		assertTrue(!result.isPresent());
		
	}
	
	
}
