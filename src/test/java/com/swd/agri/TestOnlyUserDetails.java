package com.swd.agri;

import com.swd.agri.model.Member;

public class TestOnlyUserDetails {
	
	//管理員帳號
	public static Member getRoleAdminMember() {
		
		Member member = new Member();
		
		member.setUsername("Peter");
		member.setPassword("peterfarm");
		
		return member;
		
	}
	
	//一般使用者帳號
	public static Member getRoleUserNormalMember() {
		
		Member member = new Member();
		
		member.setUsername("Sirius");
		member.setPassword("siriusfarm");
		
		return member;
		
	}
	
	//一般商家帳號
	public static Member getRoleUserMarketerMember() {
		
		Member member = new Member();
		
		member.setUsername("Lupin");
		member.setPassword("lupinfarm");
		
		return member;
		
	}

}
