package com.swd.agri.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.swd.agri.security.filter.AgriFilter;

@Configuration
@EnableWebSecurity(debug = true)
//@EnableWebSecurity
public class AgriSecurityConfig {
	
	//加密設定
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				//設定session創建機制
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				)
				//設定csrf防禦
				//X-XSRF-TOKEN自登入成功後經由每次請求由後端傳送
				//X-XSRF-TOKEN生成方法定義
				//忽略csrf防禦的功能清單
				.csrf(csrf -> csrf
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						.csrfTokenRequestHandler(createCsrfHandler())
						.ignoringRequestMatchers("/login", "/register")
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/login", "/register").permitAll()
						.requestMatchers("/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET,"/**").hasAnyRole("USER_NORMAL")
						.requestMatchers(HttpMethod.POST,"/product/**").hasAnyRole("USER_MARKETER")
						.requestMatchers(HttpMethod.PUT,"/product/**").hasAnyRole("USER_MARKETER")
						.requestMatchers(HttpMethod.DELETE,"/product/**").hasAnyRole("USER_MARKETER")
						.anyRequest().denyAll()
				)
				.cors(cors -> cors
						.configurationSource(createCorsConfig())
				)
				.addFilterAfter(new AgriFilter(), AuthorizationFilter.class)
				.build();
	}
	
	//調整CORS安全設定
	private CorsConfigurationSource createCorsConfig() {
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("*"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		//config.setAllowCredentials(true);
		config.setMaxAge(3600L);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}
	
	//防禦CSRF跨站偽造攻擊
	private CsrfTokenRequestAttributeHandler createCsrfHandler() {
		
		CsrfTokenRequestAttributeHandler handler = new CsrfTokenRequestAttributeHandler();
		handler.setCsrfRequestAttributeName(null);
		
		return handler;
		
	}

}
