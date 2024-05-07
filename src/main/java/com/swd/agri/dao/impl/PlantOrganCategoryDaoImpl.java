package com.swd.agri.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantOrganCategoryDao;
import com.swd.agri.mapper.PlantOrganCategoryRowMapper;
import com.swd.agri.model.PlantOrganCategory;

@Repository
public class PlantOrganCategoryDaoImpl implements PlantOrganCategoryDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst) {
		
		String sql = "SELECT * FROM PLANT_ORGAN";
		
		List<PlantOrganCategory> categories = jdbcTemplate.query(sql, new PlantOrganCategoryRowMapper());
		
		return categories;
		
	}

}
