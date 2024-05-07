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

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantDao;
import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.fmt.FmtTransform;
import com.swd.agri.mapper.PlantRowMapper;
import com.swd.agri.model.Plant;

@Repository
public class PlantDaoImpl implements PlantDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	FmtTransform transform;

	@Override
	public Integer createPlant(PlantCreate plantCreate) {

		String sql = "INSERT INTO PLANT(" 
				+ " PLANT_NAME, CATEGORY, DESCRIPTION, CREATE_DATE, LAST_MODIFIED_DATE"
				+ " ) VALUES (" 
				+ " :plant_name, :category, :description, :create_date, :last_modified_date" 
				+ " )";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plant_name", plantCreate.getPlantName());
		params.put("category", plantCreate.getCategory().getValue());
		params.put("description", plantCreate.getDescription());

		Date now = new Date();

		params.put("create_date", now);
		params.put("last_modified_date", now);

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(sql, new MapSqlParameterSource(params), holder);

		return holder.getKey().intValue();

	}

	@Override
	public Plant getPlantById(Integer plantId) {

		String sql = selectPlantSQL();

		sql += " AND PLANT_ID = :plant_id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plant_id", plantId);

		List<Plant> plant = jdbcTemplate.query(sql, params, new PlantRowMapper());

		if (plant.size() > 0) {
			return plant.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<Plant> getPlants(PlantQueryParams plantQueryParams) {

		String sql = selectPlantSQL();

		Map<String, Object> params = new HashMap<String, Object>();

		if (plantQueryParams.getCategory() != null && plantQueryParams.getCategory().size() > 0) {

			sql += " AND CATEGORY IN (";

			List<PlantCategoryConst> category = plantQueryParams.getCategory();

			for (int i = 0; i < category.size(); i++) {

				String param = "category_" + String.valueOf(i);

				if (i != 0)	sql += ",";

				sql += ":" + param;

				params.put(param, category.get(i).getValue());

			}

			sql += " )";

		}

		List<Plant> plants = jdbcTemplate.query(sql, params, new PlantRowMapper());

		return plants;

	}

	private String selectPlantSQL() {

		String sql = "SELECT * FROM PLANT WHERE 1 = 1";

		return sql;

	}

	@Override
	public void updatePlant(Integer plantId, PlantUpdate plantUpdate) {
		
		String sql = "UPDATE PLANT SET"
				+ " PLANT_NAME = :plant_name ,"
				+ " CATEGORY = :category ,"
				+ " DESCRIPTION = :description ,"
				+ " LAST_MODIFIED_DATE = :last_modified_date"
				+ " WHERE PLANT_ID = :plant_id"
				;
		
		//直接將物件轉換為map會使物件屬性名及SQL參數佔位符產生耦合
		//plantUpdate.setLastModifiedDate(new Date());
		//Map<String, Object> params = transform.transformToMap(plantUpdate);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plant_name", plantUpdate.getPlantName());
		params.put("category", plantUpdate.getCategory().getValue());
		params.put("description", plantUpdate.getDescription());
		params.put("last_modified_date", new Date());
		params.put("plant_id", plantId);
		
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public void deletePlant(Integer plantId) {
		
		String sql = "DELETE FROM PLANT WHERE PLANT_ID = :plant_id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plant_id", plantId);
		
		jdbcTemplate.update(sql, params);

	}

}
