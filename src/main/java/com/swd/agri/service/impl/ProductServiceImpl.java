package com.swd.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.dao.ProductDao;
import com.swd.agri.dto.ProductQueryParams;
import com.swd.agri.dto.ProductRequest;
import com.swd.agri.dto.ProductUpdate;
import com.swd.agri.model.Product;
import com.swd.agri.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public Integer createPlantProduct(ProductRequest productRequest) {
		
		Integer productId = productDao.createPlantProduct(productRequest);
		
		return productId;
	}

	@Override
	public Product getProductByProductId(Integer productId) {
		
		Product product = productDao.getProductByProductId(productId);
		
		return product;
	}

	@Override
	public List<Product> getProductsByMarketIdId(Integer marketId) {
		
		List<Product> products = productDao.getProductsByMarketIdId(marketId);
		
		return products;
	}

	@Override
	public List<Product> getProducts(ProductQueryParams params) {
		
		List<Product> products = productDao.getProducts(params);
		
		return products;
	}

	@Override
	public void updateProduct(Integer productId, ProductUpdate productUpdate) {
		
		productDao.updateProduct(productId, productUpdate);
		
	}

	@Override
	public void deleteProduct(Integer productId) {
		
		productDao.deleteProduct(productId);
		
	}

}
