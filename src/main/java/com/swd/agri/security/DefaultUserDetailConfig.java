package com.swd.agri.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class DefaultUserDetailConfig {

//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		
//		List<UserDetails> users = new ArrayList<UserDetails>();
//		
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//		
//		users.add(User.withUsername("test1")
//				.passwordEncoder(encode::encode)
////				.password("{noop}111")
//				.password("111")
//				.roles("ADMIN")
//				.build());
//		
//		users.add(User.withUsername("test2")
//				.passwordEncoder(encode::encode)
////				.password("{noop}222")
//				.password("222")
//				.roles("USER_NORMAL")
//				.build());
//
//		return new InMemoryUserDetailsManager(users);
//		
//	}
	
}
