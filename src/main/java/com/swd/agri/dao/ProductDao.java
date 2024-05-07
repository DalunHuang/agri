package com.swd.agri.dao;

import java.util.List;

import com.swd.agri.dto.ProductQueryParams;
import com.swd.agri.dto.ProductRequest;
import com.swd.agri.dto.ProductUpdate;
import com.swd.agri.model.Product;

public interface ProductDao {

	public Integer createPlantProduct(ProductRequest productRequest);

	public Product getProductByProductId(Integer productId);

	public List<Product> getProductsByMarketIdId(Integer marketId);
	
	public List<Product> getProducts(ProductQueryParams params);

	public void updateProduct(Integer productId, ProductUpdate productUpdate);

	public void deleteProduct(Integer productId);

}
