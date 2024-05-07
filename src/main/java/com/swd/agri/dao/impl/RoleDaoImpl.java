package com.swd.agri.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swd.agri.dao.RoleDao;
import com.swd.agri.mapper.RoleRowMapper;
import com.swd.agri.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getRoles(Integer accountId) {
		
		String sql = getRolesSQL() + " WHERE A.ACCOUNT_ID = :account_id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_id", accountId);
		
		List<Role> roles = jdbcTemplate.query(sql, new MapSqlParameterSource(params), new RoleRowMapper());
		
		return roles;
		
	}

	@Override
	public List<Role> getRoles(String username) {
		
		String sql = getRolesSQL() + " WHERE A.ACCOUNT_NAME = :account_name";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_name", username);
		
		List<Role> roles = jdbcTemplate.query(sql, new MapSqlParameterSource(params), new RoleRowMapper());
		
		return roles;
		
	}
	
	private String getRolesSQL() {
		
		String sql = "SELECT R.* FROM ACCOUNT_ROLE AR"				
				+ " LEFT JOIN ACCOUNT A ON A.ACCOUNT_ID = AR.ACCOUNT_ID"
				+ " LEFT JOIN [ROLE] R ON R.ROLE_ID = AR.ROLE_ID"
				;
		
		return sql;
		
	}

}
