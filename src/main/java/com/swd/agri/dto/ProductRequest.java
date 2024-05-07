package com.swd.agri.dto;

import java.util.List;

import com.swd.agri.constant.PlantOrganCategoryConst;

import jakarta.validation.constraints.NotNull;

public class ProductRequest {
	
	@NotNull 
	private String 				productName;		//產品名
	@NotNull 
	private Integer				marketId;			//商家代號
	@NotNull 
	private Integer 			plantId;			//植物代號
	@NotNull 
	private Integer				originId;			//產地
	@NotNull 
	private Integer				price;				//價格
	@NotNull 
	private Integer				stock;				//庫存
	@NotNull
	private List<PlantOrganCategoryConst> plantOrgan; //植物部位
	private String				description;		//註釋
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getMarketId() {
		return marketId;
	}
	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	public Integer getPlantId() {
		return plantId;
	}
	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}
	public Integer getOriginId() {
		return originId;
	}
	public void setOriginId(Integer originId) {
		this.originId = originId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public List<PlantOrganCategoryConst> getPlantOrgan() {
		return plantOrgan;
	}
	public void setPlantOrgan(List<PlantOrganCategoryConst> plantOrgan) {
		this.plantOrgan = plantOrgan;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
