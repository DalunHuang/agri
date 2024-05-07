package com.swd.agri.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantDao;
import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.model.Plant;

@SpringBootTest
public class PlantDaoImplTest {

	@Autowired
	private PlantDao plantDao;
	
	@Test
	@Transactional
	public void insert() {
		
		PlantCategoryConst categoryConst = PlantCategoryConst.valueOf(6);
		String plantName = "魔蘋果";
		String description = "魔蘋果的根會發出足以使成人昏厥的高頻尖叫聲，照料時需要配戴隔音效果足夠的耳罩，成熟後的魔蘋果具有解除石化效果的神奇能力";
		
		PlantCreate insertData = new PlantCreate();
		insertData.setCategory(categoryConst);
		insertData.setPlantName(plantName);
		insertData.setDescription(description);
		
		int id = plantDao.createPlant(insertData);
		
		Plant plant = plantDao.getPlantById(id);
		
		assertNotNull(plant);
		assertEquals(categoryConst, plant.getCategory());
		assertEquals(plantName, plant.getPlantName());
		assertEquals(description, plant.getDescription());
		
	}
	
	@Test
	@Transactional
	public void update() {
		
		Integer plantId = 1;
		String plantName = "測試植物update";
		PlantCategoryConst categoryConst = PlantCategoryConst.valueOf(6);
		String description = "被改變的說明";
		
		PlantUpdate plantUpdate = new PlantUpdate();
		plantUpdate.setPlantName(plantName);
		plantUpdate.setCategory(categoryConst);
		plantUpdate.setDescription(description);
		
		Plant plant = plantDao.getPlantById(plantId);
		
		assertNotNull(plant);
		assertEquals(plant.getPlantId(), plantId);
		assertNotEquals(plant.getCategory(), categoryConst);
		assertNotEquals(plant.getPlantName(), plantName);
		assertNotEquals(plant.getDescription(), description);
		
		plant = null;
		
		plantDao.updatePlant(plantId, plantUpdate);
		
		plant = plantDao.getPlantById(plantId);
		
		assertNotNull(plant);
		assertEquals(plant.getPlantId(), plantId);
		assertEquals(plant.getCategory(), categoryConst);
		assertEquals(plant.getPlantName(), plantName);
		assertEquals(plant.getDescription(), description);
		
	}
	
	@Test
	@Transactional
	public void delete() {
		
		boolean noExceptions = true;
		
		Integer id = 1;
		
		Plant plant = plantDao.getPlantById(id);
		
		assertNotNull(plant);
		
		plant = null;
		
		try {
			plantDao.deletePlant(id);			
		} catch (Exception e) {
			noExceptions = false;
		}
		
		if (noExceptions) {
			plant = plantDao.getPlantById(id);
			assertNull(plant);			
		}
		
	}
	
	@Test
	public void search() {
		
		Integer id = 1;
		String plantName = "小麥";
		
		Plant plant = plantDao.getPlantById(id);
		
		assertNotNull(plant);
		assertEquals(plantName, plant.getPlantName());
		
	}
	
	@Test
	public void searchPlants() {
		
		List<PlantCategoryConst> category = List.of(
				PlantCategoryConst.valueOf(1),
				PlantCategoryConst.valueOf(2),
				PlantCategoryConst.valueOf(3)
				);
		
		PlantQueryParams params = new PlantQueryParams();
		
		params.setCategory(category);
		
		List<Plant> plants = plantDao.getPlants(params);
		
		assertNotNull(plants);
		assertTrue(plants.stream().allMatch(plant -> category.contains(plant.getCategory())));
		
	}
	
}
