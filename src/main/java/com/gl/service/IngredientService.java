package com.gl.service;

import java.util.List;

import com.gl.model.IngredientCategory;
import com.gl.model.IngredientsItem;

public interface IngredientService {

	public IngredientCategory createIngredientCategory(String name,Long restaurantid)throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
	
	public List<IngredientCategory> findingredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientItem(Long restaurantId,String ingredientName,Long categoryId)throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
}
