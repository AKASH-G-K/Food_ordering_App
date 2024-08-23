package com.gl.service;

import java.util.List;

import com.gl.dto.RestaurantDto;
import com.gl.model.Restaurant;
import com.gl.model.User;
import com.gl.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);
   
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurants();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant  getRestaurantByUserId(Long userId)throws Exception;
	
	public RestaurantDto addToFavourites(Long restaurantId,User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
}
