package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.model.Food;
import com.gl.model.User;
import com.gl.service.FoodService;
import com.gl.service.RestaurantService;
import com.gl.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,@RequestHeader("Authorization")String jwt) throws Exception{
		User user=userService.findUserByJwtToken(jwt);
		
		List<Food> food = foodService.searchFood(name);
		
		return new ResponseEntity<>(food,HttpStatus.OK);
		
	}
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<Food>> getRestaurantFood(
			@RequestParam boolean vegetarian,
			@RequestParam boolean seasonal,
			@RequestParam boolean nonVeg,
			@RequestParam(required = false)String food_category,
			@PathVariable Long restaurantId,
			@RequestHeader("Authorization")String jwt)throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);
		
		return  new ResponseEntity<>(foods,HttpStatus.OK);
		
	}
	
}
