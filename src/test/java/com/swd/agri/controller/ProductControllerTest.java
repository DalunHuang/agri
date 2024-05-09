package com.swd.agri.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swd.agri.TestOnlyUserDetails;
import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.model.Member;
import com.swd.agri.model.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Transactional
	public void insert() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		String productName = "早晨玉米";
		Integer marketId = 1;
		Integer plantId = 2;
		Integer originId = 1;
		//String originName = "美利堅合眾國";
		String originName = "United States of America";
		Integer price = 350;
		Integer stock = 230;
		String description = "早起玉米的果實";
		//String plantOrgan = "[\"PLANT_FRUIT\", \"PLANT_SEED\"]";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("productName", productName);
		requestBody.put("marketId", marketId);
		requestBody.put("plantId", plantId);
		requestBody.put("originId", originId);
		requestBody.put("price", price);
		requestBody.put("stock", stock);
		requestBody.put("description", description);
		requestBody.put("plantOrgan", new JSONArray(
				List.of("PLANT_FRUIT", "PLANT_SEED")));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/plantProductCreate")
				.with(httpBasic(member.getUsername(),member.getPassword()))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody.toString());
		
		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.productName").value(productName))
				.andExpect(jsonPath("$.marketId").value(marketId))
				.andExpect(jsonPath("$.originName").value(originName))
				.andExpect(jsonPath("$.price").value(price))
				.andExpect(jsonPath("$.stock").value(stock))
				.andExpect(jsonPath("$.description").value(description))
				.andReturn();
		
	}
	
	@Test
	@Transactional
	public void update() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		Integer productId = 1;
		String productName = "魔蘋果";
		Integer price = 2000;
		Integer stock = 1000;
		String description = "石化剋星魔蘋果";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("productName", productName);
		requestBody.put("price", price);
		requestBody.put("stock", stock);
		requestBody.put("description", description);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/product/{productId}", productId)
				.with(httpBasic(member.getUsername(),member.getPassword()))
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody.toString());
		
		mockMvc.perform(requestBuilder)
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productName").value(productName))
			.andExpect(jsonPath("$.price").value(price))
			.andExpect(jsonPath("$.stock").value(stock))
			.andExpect(jsonPath("$.description").value(description))
			.andReturn();
		
	}
	
	@Test
	@Transactional
	public void delete() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		Integer productId = 1;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/product/{productId}", productId)
				.with(httpBasic(member.getUsername(),member.getPassword()))
				.with(csrf());
		
		mockMvc.perform(requestBuilder)
			//.andDo(print())
			.andExpect(status().isNoContent())
			.andReturn();
		
	}
	
	@Test
	public void getByProductId() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		Integer productId = 1;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/product/{productId}", productId)
				.with(httpBasic(member.getUsername(),member.getPassword()));
		
		mockMvc.perform(requestBuilder)
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productName").value("合眾小麥"))
			.andReturn();
		
	}
	
	@Test
	public void getByMarketId() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		Integer marketId = 1;
		String marketName = "合眾農場";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products/market/{marketId}", marketId)
				.characterEncoding("UTF-8")
				.with(httpBasic(member.getUsername(),member.getPassword()));
		
		MvcResult result = mockMvc
				.perform(requestBuilder)
				//.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		JSONArray jsonArray = new JSONArray(new String(result.getResponse()
								.getContentAsString(StandardCharsets.UTF_8)));
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = (JSONObject) jsonArray.get(i);
			
			assertEquals(marketId, json.getInt("marketId"));
			assertEquals(marketName, json.getString("marketName"));
			
		}
		
	}
	
	@Test
	public void getProducts() throws Exception {
		
		Member member = TestOnlyUserDetails.getRoleAdminMember();
		
		PlantCategoryConst category = PlantCategoryConst.valueOf("ANGIOSPERMS");
		PlantOrganCategoryConst organ = PlantOrganCategoryConst.valueOf("PLANT_FRUIT");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("category", category);
		requestParams.put("organ", organ);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.param("category", category.name())
				.param("organ", organ.name())
				//.contentType(MediaType.APPLICATION_JSON)
				//.content(requestParams.toString())
				.with(httpBasic(member.getUsername(),member.getPassword()))
				.with(csrf())
				;
		
		MvcResult result = mockMvc
				.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
		
		JSONArray jsonArray = new JSONArray(new String(result.getResponse()
				.getContentAsString(StandardCharsets.UTF_8)));
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject json = jsonArray.getJSONObject(i);
			
			Product product = objectMapper.readValue(
					json.toString(), Product.class);
			
			assertEquals(category, product.getPlant().getCategory());
			assertTrue(product.getPlantOrgan().contains(organ));
			
		}
		
	}
	
	
}
