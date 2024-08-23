package com.gl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.model.Category;
import com.gl.model.Restaurant;
import com.gl.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	RestaurantService restaurantService;

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurantService.findRestaurantById(id);
		return categoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Category>  optionalCategory = categoryRepository.findById(id);
		
		if(optionalCategory.isEmpty()) {
			throw new Exception("category not found");
		}
		return optionalCategory.get();
		
	}

}
