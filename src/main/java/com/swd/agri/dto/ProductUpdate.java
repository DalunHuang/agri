package com.swd.agri.dto;

import jakarta.validation.constraints.NotNull;

public class ProductUpdate {
	
	//能更新的產品資訊僅允許改名、改價格、改庫存、改說明
	
	@NotNull
	private String productName;
	@NotNull
	private Integer price;
	@NotNull
	private Integer stock;
	@NotNull
	private String description;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	

}
