package com.swd.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dao.PlantDao;
import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.model.Plant;
import com.swd.agri.model.PlantCategory;
import com.swd.agri.model.PlantOrganCategory;
import com.swd.agri.service.PlantCategoryService;
import com.swd.agri.service.PlantOrganCategoryService;
import com.swd.agri.service.PlantService;

@Service
public class PlantServicImpl implements PlantService {
	
	@Autowired
	private PlantDao plantDao;
	
	@Autowired
	private PlantCategoryService plantCategoryService;
	
	@Autowired
	private PlantOrganCategoryService plantOrganCategoryService;

	@Override
	public Integer createPlant(PlantCreate plantCreate) {
		
		Integer plantId = plantDao.createPlant(plantCreate);
		
		return plantId;
	}

	@Override
	public Plant getPlantById(Integer plantId) {
		
		Plant plant = plantDao.getPlantById(plantId);
		
		return plant;
	}

	@Override
	public List<Plant> getPlants(PlantQueryParams plantQueryParams) {
		
		List<Plant> plants = plantDao.getPlants(plantQueryParams);
		
		return plants;
	}

	@Override
	public void updatePlant(Integer plantId, PlantUpdate plantUpdate) {
		
		plantDao.updatePlant(plantId, plantUpdate);
		
	}

	@Override
	public void deletePlant(Integer plantId) {

		plantDao.deletePlant(plantId);
		
	}

	@Override
	public List<PlantCategory> getCategoryList() {
		
		List<PlantCategory> plantCategoryList = plantCategoryService.getCategoryList();
		
		return plantCategoryList;
	}

	@Override
	public List<PlantOrganCategory> getOrganCategory(PlantCategoryConst plantCategoryConst) {
		
		List<PlantOrganCategory> plantOrganCategoryList = plantOrganCategoryService.getOrganCategory(plantCategoryConst);
		
		return plantOrganCategoryList;
	}

}
