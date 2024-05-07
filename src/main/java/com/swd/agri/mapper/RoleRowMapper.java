package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.model.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Role role = new Role();
		
		role.setRoleId(rs.getInt("role_id"));
		role.setRoleName(rs.getString("role_name"));
		
		return role;
	}

}
