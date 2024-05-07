package com.swd.agri.model;

import com.swd.agri.constant.PlantCategoryConst;

public class PlantCategory {
	
	private Integer plantCategoryId;
	private PlantCategoryConst categoryName;
	private String zhDesc;
	
	public Integer getPlantCategoryId() {
		return plantCategoryId;
	}
	public void setPlantCategoryId(Integer plantCategoryId) {
		this.plantCategoryId = plantCategoryId;
	}
	public PlantCategoryConst getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(PlantCategoryConst categoryName) {
		this.categoryName = categoryName;
	}
	public String getZhDesc() {
		return zhDesc;
	}
	public void setZhDesc(String zhDesc) {
		this.zhDesc = zhDesc;
	}

}
