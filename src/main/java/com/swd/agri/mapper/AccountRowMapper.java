package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.model.Account;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Account account = new Account();
		
		account.setAccountId(rs.getInt("account_id"));
		account.setAccpuntName(rs.getString("account_name"));
		account.setBirthday(rs.getTimestamp("birthday"));
		account.seteMail(rs.getString("e_mail"));
		account.setPhoneNumber(rs.getString("phone_number"));
		account.setCreateDate(rs.getTimestamp("create_date"));
		account.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return account;
		
	}

}
