package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.model.IngredientCategory;
import com.gl.model.IngredientsItem;
import com.gl.request.IngredientCategoryRequest;
import com.gl.request.IngredientRequest;
import com.gl.service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
 
	@Autowired
	private IngredientService ingredientService;
	
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req)throws Exception{
	
		IngredientCategory item=ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
		
	}
	@PostMapping()
	public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req)throws Exception{
		IngredientsItem item = ingredientService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
		
		return new  ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/stoke")
	public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id)throws Exception{
		IngredientsItem item = ingredientService.updateStock(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id){
		List<IngredientsItem> items = ingredientService.findRestaurantsIngredients(id);
		return new ResponseEntity<>(items,HttpStatus.OK);
	}
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception{
		List<IngredientCategory> items = ingredientService.findingredientCategoryByRestaurantId(id);
		return new ResponseEntity<>(items,HttpStatus.OK);
	}
	
}
