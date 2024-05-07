package com.swd.agri.dao;

import java.util.List;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.model.PlantOrganCategory;

public interface PlantOrganCategoryDao {
	
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst);

}
