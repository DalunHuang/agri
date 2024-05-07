package com.swd.agri.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swd.agri.constant.PlantOrganCategoryConst;
import com.swd.agri.model.PlantOrganCategory;

public class PlantOrganCategoryRowMapper implements RowMapper<PlantOrganCategory>{

	@Override
	public PlantOrganCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PlantOrganCategory plantOrganCategory = new PlantOrganCategory();
		plantOrganCategory.setPlantOrganId(rs.getInt("plant_organ_id"));
		plantOrganCategory.setOrganDescription(PlantOrganCategoryConst.valueOf(rs.getString("organ_description")));
		plantOrganCategory.setZhDesc(rs.getString("zh_desc"));
		
		return plantOrganCategory;
	}

}
