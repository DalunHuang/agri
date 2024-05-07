package com.swd.agri.dao;

import java.util.List;

import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.model.Plant;

public interface PlantDao {
	
	public Integer createPlant(PlantCreate plantCreate);
	
	public Plant getPlantById(Integer plantId);
	
	public List<Plant> getPlants(PlantQueryParams plantQueryParams);
	
	public void updatePlant(Integer plantId, PlantUpdate plantUpdate);
	
	public void deletePlant(Integer plantId);

}
