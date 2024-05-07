package com.swd.agri.dao;

import com.swd.agri.model.Member;

public interface MemberDao {
	
	public Member getMemberById(String username);
	
	public Member getMemberByEmail(String email);

}
