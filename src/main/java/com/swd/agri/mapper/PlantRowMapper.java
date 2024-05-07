package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.model.Plant;

public class PlantRowMapper implements RowMapper<Plant> {

	@Override
	public Plant mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Plant plant = new Plant();
		
		plant.setPlantId(rs.getInt("plant_id"));
		plant.setPlantName(rs.getString("plant_name"));
		plant.setCategory(PlantCategoryConst.valueOf(rs.getInt("category")));
		plant.setDescription(rs.getString("description"));
		plant.setCreateDate(rs.getTimestamp("create_date"));
		plant.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return plant;
	}

}
