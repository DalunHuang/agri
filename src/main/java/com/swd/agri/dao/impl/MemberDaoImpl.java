package com.swd.agri.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swd.agri.dao.MemberDao;
import com.swd.agri.mapper.MemberRowMapper;
import com.swd.agri.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Member getMemberById(String username) {
		
		String sql = "SELECT ACCOUNT_ID, ACCOUNT_NAME, PASSWORD FROM ACCOUNT"
				+ " WHERE ACCOUNT_NAME = :account_name"
				;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_name", username);
		
		List<Member> member = jdbcTemplate.query(sql, params, new MemberRowMapper());
		
		if (member.size() > 0) {
			return member.get(0);
		} else {
			return null;			
		}
		
	}

	@Override
	public Member getMemberByEmail(String email) {
		
		String sql = "SELECT E_MAIL, PASSWORD FROM ACCOUNT"
				+ " WHERE E_MAIL = :email"
				;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		
		List<Member> member = jdbcTemplate.query(sql, params, new MemberRowMapper());
		
		if (member.size() > 0) {
			return member.get(0);
		} else {
			return null;			
		}
		
	}

}
