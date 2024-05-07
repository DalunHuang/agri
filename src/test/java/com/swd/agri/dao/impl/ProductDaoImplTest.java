package com.swd.agri.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.dao.ProductDao;
import com.swd.agri.dto.ProductQueryParams;
import com.swd.agri.dto.ProductRequest;
import com.swd.agri.dto.ProductUpdate;
import com.swd.agri.model.Plant;
import com.swd.agri.model.Product;

@SpringBootTest
public class ProductDaoImplTest {

	@Autowired
	private ProductDao productDao;
	
	@Test
	@Transactional
	public void insert() {
		
		String productName = "早晨玉米";
		Integer marketId = 1;
		Integer plantId = 2;
		Integer originId = 1;
		//String originName = "美利堅合眾國";
		String originName = "United States of America";
		Integer price = 350;
		Integer stock = 230;
		String description = "早起玉米的果實";
		List<PlantOrganCategoryConst> organs = List.of(
				PlantOrganCategoryConst.valueOf(7),
				PlantOrganCategoryConst.valueOf(8)
				);
		
		ProductRequest params = new ProductRequest();
		params.setProductName(productName);
		params.setMarketId(marketId);
		params.setPlantId(plantId);
		params.setOriginId(originId);
		params.setPrice(price);
		params.setStock(stock);
		params.setDescription(description);
		params.setPlantOrgan(organs);
		
		Integer productId = productDao.createPlantProduct(params);
		
		Product product = productDao.getProductByProductId(productId);
		
		assertNotNull(product);
		assertEquals(productName, product.getProductName());
		assertEquals(marketId, product.getMarketId());
		assertEquals(plantId, product.getPlant().getPlantId());
		assertEquals(originName, product.getOriginName());
		assertEquals(price, product.getPrice());
		assertEquals(stock, product.getStock());
		assertEquals(description, product.getDescription());
		
		
	}
	
	@Test
	@Transactional
	public void update() {
		
		Integer productId = 1;
		String productName = "魔蘋果";
		Integer price = 2000;
		Integer stock = 1000;
		String description = "石化剋星魔蘋果";
		
		Product product = productDao.getProductByProductId(productId);
		
		assertNotEquals(productName, product.getProductName());
		assertNotEquals(price, product.getPrice());
		assertNotEquals(stock, product.getStock());
		assertNotEquals(description, product.getDescription());
		
		product = null;
		
		ProductUpdate params = new ProductUpdate();
		params.setProductName(productName);
		params.setPrice(price);
		params.setStock(stock);
		params.setDescription(description);
		
		//更新產品
		productDao.updateProduct(productId, params);
		product = productDao.getProductByProductId(productId);
		
		assertEquals(productName, product.getProductName());
		assertEquals(price, product.getPrice());
		assertEquals(stock, product.getStock());
		assertEquals(description, product.getDescription());
		
	}
	
	@Test
	@Transactional
	public void deleteSuccese() {
		
		Integer productId = 1;
		
		productDao.deleteProduct(productId);
		
		Product product = productDao.getProductByProductId(productId);
		
		assertNull(product);
		
	}
	
	@Test
	public void getProductById() {
		
		Integer productId = 1;
		
		Product product = productDao.getProductByProductId(productId);
		
		assertNotNull(product);
		
	}
	
	@Test
	public void getProductByMarketIdId() {
		
		Integer marketId = 1;
		
		List<Product> products = productDao.getProductsByMarketIdId(marketId);
		
		assertNotNull(products);
		assertTrue(products.size() > 0);
		assertTrue(products.stream().allMatch(product -> product.getMarketId().equals(marketId)));
		
	}
	
	@Test
	public void getProducts() {
		
		Integer plantId = 1;
		String plantName = "小麥";
		Integer marketId = 1;
		String marketName = "合眾農場";
		PlantCategoryConst category = PlantCategoryConst.valueOf("ANGIOSPERMS");
		PlantOrganCategoryConst organ = PlantOrganCategoryConst.valueOf("PLANT_SEED");
		
		ProductQueryParams params = new ProductQueryParams();
		params.setPlantId(plantId);
		params.setPlantName(plantName);
		params.setMarketId(marketId);
		params.setMarketName(marketName);
		params.setCategory(category);
		params.setOrgan(organ);
		
		List<Product> products = productDao.getProducts(params);
		
		assertNotNull(products);
		assertTrue(products.size() > 0);
		assertTrue(products.stream().allMatch(product -> {
			
			Plant plant = product.getPlant();
			return plant.getPlantId().equals(plantId)
					&& plant.getPlantName().equals(plantName)
					&& plant.getCategory().equals(category)
					&& product.getMarketId().equals(marketId)
					&& product.getMarketName().equals(marketName)
					&& product.getPlantOrgan().contains(organ);
			
		}));
		
	}
	
	@Test
	public void getProductsNoList() {
		
		Integer plantId = 10;
		PlantCategoryConst category = PlantCategoryConst.valueOf("NON");
		PlantOrganCategoryConst organ = PlantOrganCategoryConst.valueOf("NON");
		
		ProductQueryParams params = new ProductQueryParams();
		params.setPlantId(plantId);
		params.setCategory(category);
		params.setOrgan(organ);
		
		List<Product> products = productDao.getProducts(params);
		
		assertNotNull(products);
		assertTrue(products.size() == 0);
		
	}
	
	
}
