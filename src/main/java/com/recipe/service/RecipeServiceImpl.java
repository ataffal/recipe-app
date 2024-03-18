package com.recipe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.IngredientDTO;
import com.recipe.dto.RecipeDTO;
import com.recipe.model.Ingredient;
import com.recipe.model.Recipe;
import com.recipe.repository.IngredientRepository;
import com.recipe.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<String> getAllRecipes() {
		return recipeRepository.findAll().stream().map(f -> modelMapper.map(f.getName(), String.class))
				.collect(Collectors.toList());
	}

	@Override
	public RecipeDTO getRecipeByName(String name) {
		return recipeRepository.findByName(name).map(f -> modelMapper.map(f, RecipeDTO.class)).orElseThrow();
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addRecipe(RecipeDTO recipeDTO) {
		Recipe recipe = new Recipe();
		recipe = mapRecipeDTOToEntity(recipe, recipeDTO);
		// Save the recipe
		Recipe savedRecipe = recipeRepository.save(recipe);
		List<IngredientDTO> ingredientDTOs = recipeDTO.getIngredients();
		for (IngredientDTO ingredientDTO : ingredientDTOs) {
			if (!ingredientDTO.getName().isBlank()) {
				Ingredient ingredient = mapIngredientDTOToEntity(ingredientDTO);
				ingredient.setRecipe(savedRecipe);
				ingredientRepository.save(ingredient);
			}

		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateRecipe(String name, RecipeDTO recipeDto) {
		Recipe recipe = recipeRepository.findByName(name).orElseThrow();
		recipe = mapRecipeDTOToEntity(recipe, recipeDto);
		recipeRepository.save(recipe);

	}

	private Recipe mapRecipeDTOToEntity(Recipe recipe, RecipeDTO recipeDTO) {
		// Map fields in Recipe Entity from RecipeDTO
		recipe.setName(recipeDTO.getName());
		recipe.setDescription(recipeDTO.getDescription());
		recipe.setCookingTime(recipeDTO.getCookingTime());
		recipe.setMealType(recipeDTO.getMealType());
		return recipe;
	}

	private Ingredient mapIngredientDTOToEntity(IngredientDTO ingredientDTO) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		ingredient.setAmount(ingredientDTO.getAmount());
		return ingredient;
	}

	@Override
	public List<RecipeDTO> searchRecipes(String name, String mealtype) {
		if (name == null && mealtype == null) {
			return recipeRepository.findAll().stream().map(f -> modelMapper.map(f, RecipeDTO.class))
					.collect(Collectors.toList());
		} else {
			return recipeRepository.findByAttributes(name, mealtype).stream()
					.map(f -> modelMapper.map(f, RecipeDTO.class)).collect(Collectors.toList());
		}
	}

}