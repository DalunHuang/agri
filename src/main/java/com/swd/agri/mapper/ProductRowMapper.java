package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.model.Plant;
import com.swd.agri.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
				
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setMarketId(rs.getInt("market_id"));
		product.setMarketName(rs.getString("market_name"));
		
		Plant plant = new Plant();
		plant.setPlantId(rs.getInt("plant_id"));
		plant.setPlantName(rs.getString("plant_name"));
		plant.setCategory(PlantCategoryConst.valueOf(rs.getString("category")));
		
		product.setPlant(plant);
		
		product.setPlantOrgan(Arrays.asList(rs.getString("organ").split("｜"))
									.stream().map(organ -> {
										return PlantOrganCategoryConst.valueOf(organ);
									}).collect(Collectors.toList()));
		product.setPrice(rs.getInt("price"));
		product.setStock(rs.getInt("stock"));
		product.setDescription(rs.getString("description"));
		product.setOriginName(rs.getString("origin_name"));
		product.setCreateDate(rs.getTimestamp("create_date"));
		product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		
		return product;
	}

}
