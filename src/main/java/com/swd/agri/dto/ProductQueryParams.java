package com.swd.agri.dto;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;

public class ProductQueryParams {
	
	private Integer 		plantId;
	private String 			plantName;
	private Integer 		marketId;
	private String 			marketName;
	private PlantCategoryConst 	category;
	private PlantOrganCategoryConst organ;
	
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
	public Integer getMarketId() {
		return marketId;
	}
	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public PlantCategoryConst getCategory() {
		return category;
	}
	public void setCategory(PlantCategoryConst category) {
		this.category = category;
	}
	public PlantOrganCategoryConst getOrgan() {
		return organ;
	}
	public void setOrgan(PlantOrganCategoryConst organ) {
		this.organ = organ;
	}
	
}
