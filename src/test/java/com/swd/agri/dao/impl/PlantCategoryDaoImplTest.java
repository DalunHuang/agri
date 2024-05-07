package com.swd.agri.dao.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swd.agri.dao.PlantCategoryDao;
import com.swd.agri.model.PlantCategory;

@SpringBootTest
public class PlantCategoryDaoImplTest {

	@Autowired
	private PlantCategoryDao plantCategoryDao;
	
	@Test
	public void getCategoryList() {
		
		List<PlantCategory> category = plantCategoryDao.getCategoryList();
		
		assertNotNull(category);
		assertTrue(category.size() > 0);
		
	}
	
}
