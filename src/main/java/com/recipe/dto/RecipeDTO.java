package com.recipe.dto;

import java.util.List;

import lombok.Data;

@Data
public class RecipeDTO {

	private String name;
	private String description;
	private List<IngredientDTO> ingredients;
	private int cookingTime;
	private String mealType;
}
