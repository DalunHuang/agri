package com.swd.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantOrganCategoryDao;
import com.swd.agri.model.PlantOrganCategory;
import com.swd.agri.service.PlantOrganCategoryService;

@Service
public class PlantOrganCategoryServiceImpl implements PlantOrganCategoryService {
	
	@Autowired
	private PlantOrganCategoryDao plantOrganCategoryDao;

	@Override
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst) {
		
		List<PlantOrganCategory> plantOrganCategorys = plantOrganCategoryDao.getOrganCategory(plantCategoryConst);
		
		return plantOrganCategorys;
	}

}
