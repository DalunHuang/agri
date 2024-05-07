package com.swd.agri.model;

import com.swd.agri.constant.PlantOrganCategoryConst;

public class PlantOrganCategory {
	
	private Integer plantOrganId;
	private PlantOrganCategoryConst organDescription;
	private String zhDesc;
	
	public Integer getPlantOrganId() {
		return plantOrganId;
	}
	public void setPlantOrganId(Integer plantOrganId) {
		this.plantOrganId = plantOrganId;
	}
	public PlantOrganCategoryConst getOrganDescription() {
		return organDescription;
	}
	public void setOrganDescription(PlantOrganCategoryConst organDescription) {
		this.organDescription = organDescription;
	}
	public String getZhDesc() {
		return zhDesc;
	}
	public void setZhDesc(String zhDesc) {
		this.zhDesc = zhDesc;
	}
	
}
