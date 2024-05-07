package com.swd.agri.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.swd.agri.dao.MemberDao;
import com.swd.agri.dao.RoleDao;
import com.swd.agri.model.Member;

@Component
public class AgriUserDetailsService implements UserDetailsService {
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberDao.getMemberById(username);
		
		if (member != null) {
			String memberUsername = member.getUsername();
			String memberPassword = member.getPassword();
			
			//權限設定
			List<GrantedAuthority> authorities = roleDao.getRoles(member.getAccountId())
					.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
					.collect(Collectors.toList());
			
			return new User(memberUsername, memberPassword, authorities);
			
		} else {
			
			throw new UsernameNotFoundException("Member not found for:" + username);
			
		}
		
	}
	
}
