package com.recipe.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.recipe.dto.RecipeDTO;

@Validated
public interface RecipeService {

	List<String> getAllRecipes();

	void addRecipe(RecipeDTO recipeDTO);

	void updateRecipe(String name, RecipeDTO recipeDTO);

	RecipeDTO getRecipeByName(String name);

	List<RecipeDTO> searchRecipes(String name, String mealtype);
}
