package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
List<IngredientsItem> findByRestaurantId(Long id);
}
