package com.swd.agri.model;

import java.util.Date;
import java.util.List;

import com.swd.agri.constant.PlantOrganCategoryConst;

public class Product {
	
	private Integer				productId;			//產品代號
	private String 				productName;		//產品名
	private Integer				marketId;			//商家代號
	private String 				marketName;			//商家名
	private Plant	 			plant;				//植物資訊
	private List<PlantOrganCategoryConst> plantOrgan; //植物部位
	private Integer				price;				//價格
	private Integer				stock;				//庫存
	private String				description;		//註釋
	private String				originName;			//產地
	private Date				createDate;			//建立日期
	private Date				lastModifiedDate;	//最後異動日
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
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
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	public List<PlantOrganCategoryConst> getPlantOrgan() {
		return plantOrgan;
	}
	public void setPlantOrgan(List<PlantOrganCategoryConst> plantOrgan) {
		this.plantOrgan = plantOrgan;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
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
