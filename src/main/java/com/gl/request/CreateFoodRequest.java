package com.gl.request;

import java.util.List;

import com.gl.model.Category;
import com.gl.model.IngredientsItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> images;
	
	private Long restaurantId;
	private boolean vegetarian;
	private boolean seasonal;
	
	private List<IngredientsItem> ingredients;
	
	
}
