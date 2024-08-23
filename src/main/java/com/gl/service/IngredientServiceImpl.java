package com.gl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.model.IngredientCategory;
import com.gl.model.IngredientsItem;
import com.gl.model.Restaurant;
import com.gl.repository.IngredientCategoryRepository;
import com.gl.repository.IngredientItemRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	@Autowired
	private IngredientItemRepository ingredientItemRepository;
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantid) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantid);
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<IngredientCategory> opt= ingredientCategoryRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("Ingredient not found with the id" + id);
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findingredientCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		restaurantService.findRestaurantById(id);
		
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant  = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setCategory(category);
		item.setRestaurant(restaurant);
		IngredientsItem ingredient  = ingredientItemRepository.save(item);
		category.getIgredients().add(ingredient);
		return ingredient;
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
		// TODO Auto-generated method stub
		return ingredientItemRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<IngredientsItem> optionalIngredientItem = ingredientItemRepository.findById(id);
		if(optionalIngredientItem.isEmpty()) {
			throw new Exception("ingredient not found");
		}
		IngredientsItem ingredientsItem = optionalIngredientItem.get();
		ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
		
		return ingredientItemRepository.save(ingredientsItem);
	}

}
