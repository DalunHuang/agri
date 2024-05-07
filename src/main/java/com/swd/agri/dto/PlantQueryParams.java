package com.swd.agri.dto;

import java.util.List;

import com.swd.agri.constant.PlantCategoryConst;

public class PlantQueryParams {
	
	List<PlantCategoryConst> category;
	
	public List<PlantCategoryConst> getCategory() {
		return category;
	}
	public void setCategory(List<PlantCategoryConst> category) {
		this.category = category;
	}
	
}
