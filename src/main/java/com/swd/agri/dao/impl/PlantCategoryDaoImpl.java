package com.swd.agri.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swd.agri.dao.PlantCategoryDao;
import com.swd.agri.mapper.PlantCategoryRowMapper;
import com.swd.agri.model.PlantCategory;

@Repository
public class PlantCategoryDaoImpl implements PlantCategoryDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<PlantCategory> getCategoryList() {
		
		String sql = "SELECT * FROM PLANT_CATEGORY";
		
		List<PlantCategory> categories = jdbcTemplate.query(sql, new PlantCategoryRowMapper());
		
		return categories;
	}

}
