package com.swd.agri.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.dao.ProductDao;
import com.swd.agri.dto.ProductQueryParams;
import com.swd.agri.dto.ProductRequest;
import com.swd.agri.dto.ProductUpdate;
import com.swd.agri.mapper.ProductRowMapper;
import com.swd.agri.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Integer createPlantProduct(ProductRequest productRequest) {
		
		String sql = "INSERT INTO　PRODUCT ("
				+ " PRODUCT_NAME, MARKET_ID, PLANT_ID, ORIGIN_ID, PRICE, STOCK, DESCRIPTION, "
				+ " CREATE_DATE, LAST_MODIFIED_DATE"
				+ " ) VALUES ("
				+ " :productName, :marketId, :plantId, :originId, :price, :stock, :description, "
				+ " :createDate, :lastModifiedDate"
				+ " )";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productName", productRequest.getProductName());
		params.put("marketId", productRequest.getMarketId());
		params.put("plantId", productRequest.getPlantId());
		params.put("originId", productRequest.getOriginId());
		params.put("price", productRequest.getPrice());
		params.put("stock", productRequest.getStock());
		params.put("description", productRequest.getDescription());
		
		Date now = new Date();
		params.put("createDate", now);
		params.put("lastModifiedDate", now);
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, new MapSqlParameterSource(params), holder);
		
		Integer plantId = holder.getKey().intValue();
		
		//新增植物販售部位數據
		List<PlantOrganCategoryConst> organs = productRequest.getPlantOrgan();
		params.clear();
		params.put("plantId", plantId);
		
		sql = "INSERT INTO PRODUCT_PLANT_ORGAN(PRODUCT_ID, PLANT_ORGAN_ID) VALUES ";
		
		for (int i = 0; i < organs.size(); i++) {
			
			PlantOrganCategoryConst organ = organs.get(i);
			String organParam = "organ_" + i;
			
			if (i != 0) {
				sql += ",";
			}
			
			sql += " (:plantId, :" + organParam + ") ";
			params.put(organParam, organ.getValue());
			
		}
		
		jdbcTemplate.update(sql, new MapSqlParameterSource(params));
		
		return plantId;
		
	}

	@Override
	public Product getProductByProductId(Integer productId) {
		
		String sql = selectProductsSQL();
				
		sql += " AND PRODUCT_ID = :product_id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product_id", productId);
		
		List<Product> product = jdbcTemplate.query(sql, params, new ProductRowMapper());
		
		if (product.size() > 0) {
			return product.get(0);
		} else {
			return null;			
		}
		
	}

	@Override
	public List<Product> getProductsByMarketIdId(Integer marketId) {
		
		String sql = selectProductsSQL();
		
		sql += " AND P.MARKET_ID = :market_id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("market_id", marketId);
		
		List<Product> products = jdbcTemplate.query(sql, params, new ProductRowMapper());
		
		return products;
		
	}

	@Override
	public List<Product> getProducts(ProductQueryParams queryParams) {
		
		String sql = selectProductsSQL();
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (queryParams.getPlantId() != null) {
			sql += " AND PL.PLANT_ID = :plant_id";
			params.put("plant_id", queryParams.getPlantId());
		}
		
		if (queryParams.getPlantName() != null) {
			sql += " AND PL.PLANT_NAME LIKE :plant_name";
			params.put("plant_name", "%" + queryParams.getPlantName() + "%");
		}
		
		if (queryParams.getMarketId() != null) {
			sql += " AND M.MARKET_ID = :market_id";
			params.put("market_id", queryParams.getMarketId());
		}
		
		if (queryParams.getMarketName() != null) {
			sql += " AND M.MARKET_NAME = :market_name";
			params.put("market_name", queryParams.getMarketName());
		}
		
		if (!"NON".equals(queryParams.getCategory().name())) {
			sql += " AND PC.CATEGORY_NAME = :category";
			params.put("category", queryParams.getCategory().name());
		}
		
		if (!"ALL".equals(queryParams.getOrgan().name()) &&
			!"NON".equals(queryParams.getOrgan().name())) 
		{
			//sql += " AND :organ IN (SELECT ORGAN_DESCRIPTION FROM PLANT_ORGAN WHERE PLANT_ORGAN_ID IN  ";
			
			sql += " AND EXISTS ( SELECT PO.ORGAN_DESCRIPTION FROM PRODUCT_PLANT_ORGAN PPO"
					+ " LEFT JOIN PLANT_ORGAN PO ON PPO.PLANT_ORGAN_ID = PO.PLANT_ORGAN_ID"
					+ " WHERE P.PRODUCT_ID = PPO.PRODUCT_ID AND PO.ORGAN_DESCRIPTION = :organ )";
			
			params.put("organ", queryParams.getOrgan().name());
		}
		
		List<Product> products = jdbcTemplate.query(sql, 
				new MapSqlParameterSource(params), 
				new ProductRowMapper());
		
		return products;
		
	}
	
	
	
	private String selectProductsSQL() {
		
		String sql = "SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PRICE, P.STOCK, P.DESCRIPTION,"
				+ " PL.PLANT_ID, PL.PLANT_NAME,"
				+ " PC.CATEGORY_NAME AS CATEGORY,"
				+ " STUFF((SELECT CONCAT('｜', (SELECT ORGAN_DESCRIPTION FROM PLANT_ORGAN PO WHERE PLANT_ORGAN_ID = PPO.PLANT_ORGAN_ID))"
				+ "  FROM PRODUCT_PLANT_ORGAN PPO WHERE PPO.PRODUCT_ID = P.PRODUCT_ID FOR XML PATH('')) , 1, 1, '') AS ORGAN,"
				+ " M.MARKET_ID, M.MARKET_NAME,"
				+ " O.ORIGIN_NAME,"
				+ " P.CREATE_DATE, P.LAST_MODIFIED_DATE"
				+ " FROM PRODUCT P"
				+ " LEFT JOIN MARKET M ON P.MARKET_ID = M.MARKET_ID"
				+ " LEFT JOIN PLANT PL ON P.PLANT_ID = PL.PLANT_ID"
				+ " LEFT JOIN ORIGIN O ON P.ORIGIN_ID = O.ORIGIN_ID"
				+ " LEFT JOIN PLANT_CATEGORY PC ON PL.CATEGORY = PC.PLANT_CATEGORY_ID"
				+ " WHERE 1 = 1"
				;
		
		return sql;
		
	}

	@Override
	public void updateProduct(Integer productId, ProductUpdate productUpdate) {
		
		String sql = "UPDATE PRODUCT SET "
				+ " PRODUCT_NAME = :product_name ,"
				+ " PRICE = :price ,"
				+ " STOCK = :stock ,"
				+ " DESCRIPTION = :description ,"
				+ " LAST_MODIFIED_DATE = :last_modified_date"
				+ " WHERE PRODUCT_ID = :product_id"
				;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product_name", productUpdate.getProductName());
		params.put("price", productUpdate.getPrice());
		params.put("stock", productUpdate.getStock());
		params.put("description", productUpdate.getDescription());
		params.put("last_modified_date", new Date());
		params.put("product_id", productId);
		
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void deleteProduct(Integer productId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product_id", productId);
		
		//刪除產品相關子table
		String sql = "DELETE FROM PRODUCT_PLANT_ORGAN WHERE PRODUCT_ID = :product_id";
		
		jdbcTemplate.update(sql, params);
		
		//刪除產品資訊
		sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = :product_id";
		
		jdbcTemplate.update(sql, params);
		
	}
	

}
