package com.swd.agri.dao.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantOrganCategoryDao;
import com.swd.agri.model.PlantOrganCategory;

@SpringBootTest
public class PlantOrganCategoryDaoImplTest {

	@Autowired
	private PlantOrganCategoryDao plantOrganCategoryDao;
	
	@Test
	public void getOrganCategory() {
		
		PlantCategoryConst categoryConst = PlantCategoryConst.valueOf(1);
		
		List<PlantOrganCategory> category = plantOrganCategoryDao.getOrganCategory(categoryConst);
		
		assertNotNull(category);
		assertTrue(category.size() > 0);
		
	}
	
}
