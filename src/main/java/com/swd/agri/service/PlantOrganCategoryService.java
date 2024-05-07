package com.swd.agri.service;

import java.util.List;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.model.PlantOrganCategory;

public interface PlantOrganCategoryService {
	
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst);

}
