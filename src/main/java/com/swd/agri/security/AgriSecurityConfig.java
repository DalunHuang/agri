package com.swd.agri.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AgriSecurityConfig {
	
	//加密設定
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request
						//.requestMatchers("/login").permitAll()
						//.requestMatchers("/**").hasRole("ADMIN")
						//.requestMatchers("/hello").hasAnyRole("USER_NORMAL")
						//.anyRequest().denyAll()
						.anyRequest().permitAll()
				)
				.build();
	}

}
