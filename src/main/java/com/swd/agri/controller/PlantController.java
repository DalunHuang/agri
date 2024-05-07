package com.swd.agri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swd.agri.constant.PlantCategoryConst;
import com.swd.agri.dto.PlantCreate;
import com.swd.agri.dto.PlantQueryParams;
import com.swd.agri.dto.PlantUpdate;
import com.swd.agri.model.Plant;
import com.swd.agri.model.PlantCategory;
import com.swd.agri.model.PlantOrganCategory;
import com.swd.agri.service.PlantService;

import jakarta.validation.Valid;

@RestController
public class PlantController {

	@Autowired
	private PlantService plantService;
	
	@PostMapping("/plantCreate")
	public ResponseEntity<Plant> createPlant(
		@RequestBody @Valid PlantCreate plantCreate
	){
		
		Integer plantId = plantService.createPlant(plantCreate);
		
		Plant plant = plantService.getPlantById(plantId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(plant);
		
	}
	
	
	@GetMapping("/plant/{plantId}")
	public ResponseEntity<Plant> getPlantById(
		@PathVariable Integer plantId	
	){
		
		Plant plant = plantService.getPlantById(plantId);
		
		if (plant != null) {
			return ResponseEntity.status(HttpStatus.OK).body(plant);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	
	@RequestMapping("/plants")
	public ResponseEntity<List<Plant>> getPlants(
		@RequestBody @Valid PlantQueryParams queryParams	
	){
		
		List<Plant> plants = plantService.getPlants(queryParams);
		
		return ResponseEntity.status(HttpStatus.OK).body(plants);
		
	}
	
	
	@GetMapping("/plant/category")
	public ResponseEntity<List<PlantCategory>> getPlantCategory(){
		
		List<PlantCategory> plantCategorys = plantService.getCategoryList();
		
		return ResponseEntity.status(HttpStatus.OK).body(plantCategorys);
		
	}
	
	
	@GetMapping("/plant/{category}/organ")
	public ResponseEntity<List<PlantOrganCategory>> getPlantOrganCategory(
		@PathVariable String category	
	){
		
		List<PlantOrganCategory> plantCategorys = plantService.getOrganCategory(PlantCategoryConst.valueOf(category));
		
		return ResponseEntity.status(HttpStatus.OK).body(plantCategorys);
		
	}
	
	
	@PutMapping("/plant/{plantId}")
	public ResponseEntity<Plant> updatePlant(
		@PathVariable Integer plantId,
		@RequestBody PlantUpdate plantUpdate
	){
		plantService.updatePlant(plantId, plantUpdate);
		
		Plant plant = plantService.getPlantById(plantId);
		
		return ResponseEntity.status(HttpStatus.OK).body(plant);
		
	}
	
	
	@DeleteMapping("/plant/{plantId}")
	public ResponseEntity<?> deletePlant(
		@PathVariable Integer plantId
	){
		
		plantService.deletePlant(plantId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	
	
}
