package com.swd.agri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.dto.ProductQueryParams;
import com.swd.agri.dto.ProductRequest;
import com.swd.agri.dto.ProductUpdate;
import com.swd.agri.model.Product;
import com.swd.agri.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/plantProductCreate")
	public ResponseEntity<Product> createPlantProduct(
			@RequestBody @Valid
			ProductRequest productRequest
	){
		
		Integer id = productService.createPlantProduct(productRequest);
		
		Product product = productService.getProductByProductId(id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductByProductId(
		@PathVariable Integer productId
	){
		
		Product product = productService.getProductByProductId(productId);
		
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	
	@GetMapping("/products/market/{marketId}")
	public ResponseEntity<List<Product>> getProductsByMerchantId(
		@PathVariable Integer marketId
	){
		List<Product> products = productService.getProductsByMarketIdId(marketId);
		
		return ResponseEntity.status(HttpStatus.OK).body(products);
		
	}
	
	
	@RequestMapping("/products")
	public ResponseEntity<List<Product>> getProducts (
		@RequestParam String category,
		@RequestParam String organ
	){
		
		ProductQueryParams params = new ProductQueryParams();
		params.setCategory(PlantCategoryConst.valueOf(category));
		params.setOrgan(PlantOrganCategoryConst.valueOf(organ));
		
		List<Product> products = productService.getProducts(params);
		
		return ResponseEntity.status(HttpStatus.OK).body(products);
		
	}
	
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<Product> updateProduct(
		@PathVariable Integer productId,
		@RequestBody ProductUpdate productUpdate	
	){
		productService.updateProduct(productId, productUpdate);
		
		Product product = productService.getProductByProductId(productId);
		
		return ResponseEntity.status(HttpStatus.OK).body(product);
		
	}
	
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<?> deleteProduct(
		@PathVariable Integer productId	
	){
		productService.deleteProduct(productId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	

}
