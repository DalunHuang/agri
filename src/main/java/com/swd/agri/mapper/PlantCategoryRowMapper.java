package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.model.PlantCategory;

public class PlantCategoryRowMapper implements RowMapper<PlantCategory>{

	@Override
	public PlantCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PlantCategory plantCategory = new PlantCategory();
		
		plantCategory.setPlantCategoryId(rs.getInt("plant_category_id"));
		plantCategory.setCategoryName(PlantCategoryConst.valueOf(rs.getString("category_name")));
		plantCategory.setZhDesc(rs.getString("zh_desc"));
		
		return plantCategory;
	}

}
