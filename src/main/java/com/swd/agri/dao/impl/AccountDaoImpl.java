package com.swd.agri.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.swd.agri.dao.AccountDao;
import com.swd.agri.mapper.AccountRowMapper;
import com.swd.agri.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Integer register(Account account) {
		
		String sql = "INSERT INTO ACCOUNT("
				+ " ACCOUNT_NAME, PASSWORD, E_MAIL, CREATE_DATE, LAST_MODIFIED_DATE"
				+ ") VALUES ("
				+ " :account_name, :password, :e_mail, :create_date, :last_modified_date"
				+ ")"
				;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("create_date", account.getAccpuntName());
		params.put("password", account.getPassword());
		params.put("e_mail", account.geteMail());
		
		Date now = new Date();
		params.put("create_date", now);
		params.put("last_modified_date", now);
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, new MapSqlParameterSource(params), holder);
		
		return holder.getKey().intValue();
	}

	@Override
	public Account getAccountById(Integer accountId) {
		
		String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = :account_id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account_id", accountId);
		
		List<Account> account = jdbcTemplate.query(sql, new MapSqlParameterSource(params), new AccountRowMapper());

		if (account.size() > 0) {
			return account.get(0);
		} else {
			return null;
		}
		
	}

}
