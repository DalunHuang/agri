package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.model.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Member member = new Member();
		
		member.setAccountId(rs.getInt("account_id"));
		member.setUsername(rs.getString("account_name"));
		member.setPassword(rs.getString("password"));
		
		return member;
	}

}
