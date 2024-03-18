package com.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.dto.RecipeDTO;
import com.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping("")
	public String getRecipeOverview(Model model) {
		List<String> listOfRecipesName = recipeService.getAllRecipes();
		model.addAttribute("recipeNames", listOfRecipesName);
		return "overview";
	}

	@GetMapping("/{name}")
	public String getRecipeDetails(@PathVariable("name") String name, Model model) {
		RecipeDTO recipeDTO = recipeService.getRecipeByName(name);
		model.addAttribute("recipeDTO", recipeDTO);
		return "recipe-details";

	}

	@PostMapping("/add")
	public String addRecipe(@ModelAttribute("recipeDTO") RecipeDTO recipeDTO) {
		recipeService.addRecipe(recipeDTO);
		return "redirect:/recipes";
	}

	@GetMapping("/create")
	public String showRecipeForm(Model model) {
		// Create a new Recipe object and add it to the model
		model.addAttribute("recipeDTO", new RecipeDTO());
		return "create-recipe";
	}

	@PostMapping("/edit/{name}")
	public String editRecipe(@PathVariable("name") String name, @ModelAttribute("recipeDTO") RecipeDTO recipeDTO) {
		recipeService.updateRecipe(name, recipeDTO);
		return "redirect:/recipes";
	}

	@GetMapping("/update")
	public String showRecipeForm(@RequestParam String name, Model model) {
		RecipeDTO recipeDTO = recipeService.getRecipeByName(name);
		model.addAttribute("recipeDTO", recipeDTO);
		return "update-recipe";
	}

	@GetMapping("/search")
	public String searchRecipes(@RequestParam(required = false) String name,
			@RequestParam(required = false) String mealtype, Model model) {
		List<RecipeDTO> listOfRecipes = recipeService.searchRecipes(name, mealtype);
		model.addAttribute("listOfRecipes", listOfRecipes);
		return "search-result";
	}

	@GetMapping("/search-page")
	public String getSearchPage() {
		return "search-page";
	}
}
