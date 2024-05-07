package com.swd.agri.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.model.Plant;
import com.swd.agri.model.PlantCategory;
import com.swd.agri.model.PlantOrganCategory;

@SpringBootTest
@AutoConfigureMockMvc
public class PlantControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Transactional
	public void insert() throws Exception {
		
		String plantName = "魔蘋果";
		PlantCategoryConst category = PlantCategoryConst.valueOf(2); //果實
		String description = "尖叫聲難以忍受的蘋果";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("plantName", plantName);
		requestBody.put("category", category.name());
		requestBody.put("description", description);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/plantCreate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody.toString());
		
		mockMvc.perform(requestBuilder)
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.plantName").value(plantName))
		.andExpect(jsonPath("$.category").value(category.name()))
		.andExpect(jsonPath("$.description").value(description))
		.andReturn();
		
	}
	
	@Test
	@Transactional
	public void update() throws Exception {
		
		Integer plantId = 1;
		String plantName = "魔蘋果";
		PlantCategoryConst category = PlantCategoryConst.valueOf(3);
		String description = "尖叫聲難以忍受的蘋果";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("plantName", plantName);
		requestBody.put("category", category.name());
		requestBody.put("description", description);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/plant/{plantId}", plantId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody.toString());
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.plantId").value(plantId))
			.andExpect(jsonPath("$.plantName").value(plantName))
			.andExpect(jsonPath("$.category").value(category.name()))
			.andExpect(jsonPath("$.description").value(description))
			.andReturn();
		
	}
	
	@Test
	@Transactional
	public void delete() throws Exception {
		
		Integer plantId = 1; //被產品大量使用的植物資訊應當無法被刪除
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/plant/{plantId}", plantId);
		
		assertThrows(Exception.class, () -> 
			mockMvc.perform(requestBuilder).andReturn());
		
	}
	
	@Test
	public void getById() throws Exception {
		
		Integer plantId = 1;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/plant/{plantId}", plantId);
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.plantId").value(plantId))
			.andReturn();
		
	}
	
	@Test
	public void getPlants() throws Exception {
		
		List<PlantCategoryConst> category = List.of(
				PlantCategoryConst.valueOf(2),
				PlantCategoryConst.valueOf(3)				
				);
		
		JSONArray array = new JSONArray(category);
		JSONObject requestBody = new JSONObject();
		requestBody.put("queryParams", array);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/plants")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody.toString());
		
		MvcResult result = mockMvc
				.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		JSONArray jsonArray = new JSONArray(new String(result.getResponse()
				.getContentAsString(StandardCharsets.UTF_8)));
		
		for (int i = 0; i < jsonArray.length(); i++) {
			Plant plant = objectMapper.readValue(jsonArray.get(i).toString(), Plant.class);
			assertTrue(category.contains(plant.getCategory()));
		}
		
	}
	
	@Test
	public void plantCategoryExist() throws Exception {
		
		//僅測試是否有資料
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/plant/category");
		
		assertDoesNotThrow(() -> {
			mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		});
		
	}
	
	@Test
	public void getPlantCategory() throws Exception {
		
		//測試資料正確度
		
		List<PlantCategoryConst> category = List.of(
				PlantCategoryConst.valueOf(1),
				PlantCategoryConst.valueOf(2),
				PlantCategoryConst.valueOf(3),
				PlantCategoryConst.valueOf(4),
				PlantCategoryConst.valueOf(5),
				PlantCategoryConst.valueOf(6)
				);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/plant/category");
		
		MvcResult result = mockMvc
				.perform(requestBuilder)
				//.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		JSONArray jsonArray = new JSONArray(new String(result.getResponse()
				.getContentAsString(StandardCharsets.UTF_8)));
		
		for (int i = 0; i < jsonArray.length(); i++) {
			PlantCategory plant = objectMapper.readValue(jsonArray.get(i).toString(), PlantCategory.class);
			assertTrue(category.contains(plant.getCategoryName()));
		}
		
	}
	
	@Test
	public void getPlantOrganCategoryExist() throws Exception {
		
		PlantCategoryConst category = PlantCategoryConst.valueOf(2);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/plant/{category}/organ", category.name());
		
		assertDoesNotThrow(() -> {
			mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		});
		
	}
	
	@Test
	public void getPlantOrganCategory() throws Exception {
		
		//測試資料正確度
		
		List<PlantOrganCategoryConst> organCategory = List.of(
				PlantOrganCategoryConst.valueOf(1),
				PlantOrganCategoryConst.valueOf(2),
				PlantOrganCategoryConst.valueOf(3),
				PlantOrganCategoryConst.valueOf(4),
				PlantOrganCategoryConst.valueOf(5),
				PlantOrganCategoryConst.valueOf(6),
				PlantOrganCategoryConst.valueOf(7),
				PlantOrganCategoryConst.valueOf(8)
				);
		
		PlantCategoryConst category = PlantCategoryConst.valueOf(2);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/plant/{category}/organ", category.name());
		
		MvcResult result = mockMvc
				.perform(requestBuilder)
				//.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		JSONArray jsonArray = new JSONArray(new String(result.getResponse()
				.getContentAsString(StandardCharsets.UTF_8)));
		
		for (int i = 0; i < jsonArray.length(); i++) {
			PlantOrganCategory plant = objectMapper.readValue(jsonArray.get(i).toString(), PlantOrganCategory.class);
			assertTrue(organCategory.contains(plant.getOrganDescription()));
		}
		
	}
	

}
