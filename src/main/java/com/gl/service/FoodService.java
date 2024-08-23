package com.gl.service;

import java.util.List;

import com.gl.model.Category;
import com.gl.model.Food;
import com.gl.model.Restaurant;
import com.gl.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req, Category category,Restaurant restaurant);
	void deleteFood(Long foodId) throws Exception;
	public List<Food> getRestaurantsFood(Long restaurantId,boolean isVegeterian,boolean isNonveg,boolean isSeasonal,String foodCategory);
	public List<Food> searchFood(String keyword);
	public Food findFoodById(Long foodId) throws Exception;
	public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
