package com.swd.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.dao.PlantCategoryDao;
import com.swd.agri.model.PlantCategory;
import com.swd.agri.service.PlantCategoryService;

@Service
public class PlantCategoryServiceImpl implements PlantCategoryService {
	
	@Autowired
	private PlantCategoryDao plantCategoryDao;

	@Override
	public List<PlantCategory> getCategoryList() {
		
		List<PlantCategory> plantCategorys = plantCategoryDao.getCategoryList();
		
		return plantCategorys;
	}

}
