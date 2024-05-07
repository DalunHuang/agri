package com.swd.agri.model;

import java.util.Date;

import com.swd.agri.constant.PlantCategoryConst;

public class Plant {
	
	private Integer 		plantId;
	private String 			plantName;
	private PlantCategoryConst 	category;
	private String			description;
	private Date 			createDate;
	private Date 			lastModifiedDate;
	
	public Integer getPlantId() {
		return plantId;
	}
	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
}
