package com.swd.agri.dto;

import com.swd.agri.constant.PlantCategoryConst;

public class PlantCreate {
	
	private String plantName;
	private PlantCategoryConst category;
	private String description;
	
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public PlantCategoryConst getCategory() {
		return category;
	}
	public void setCategory(PlantCategoryConst category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
