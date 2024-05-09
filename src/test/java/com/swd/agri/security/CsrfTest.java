package com.swd.agri.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.swd.agri.TestOnlyUserDetails;
import com.swd.agri.model.Member;

@SpringBootTest
@AutoConfigureMockMvc
public class CsrfTest {
	
	//spring security 預設 csrf 修改數據請求生效(除GET外)

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCsrfNotHaveToken() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/csrftest")
				.with(httpBasic(member.getUsername(),member.getPassword()));
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().is(403));
		
	}
	
	@Test
	public void testCsrfHaveToken() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/csrftest")
				.with(httpBasic(member.getUsername(),member.getPassword()))
				.with(csrf());
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().is(200));
		
	}
	
	
	
}
