package com.swd.agri.service;

import java.util.List;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.model.Plant;
import com.swd.agri.model.PlantCategory;
import com.swd.agri.model.PlantOrganCategory;

public interface PlantService {
	
	public Integer createPlant(PlantCreate plantCreate);
	
	public Plant getPlantById(Integer plantId);
	
	public List<Plant> getPlants(PlantQueryParams plantQueryParams);
	
	public void updatePlant(Integer plantId, PlantUpdate plantUpdate);
	
	public void deletePlant(Integer plantId);
	
	public List<PlantCategory> getCategoryList();
	
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst);
	
}
