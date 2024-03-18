package com.recipe.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RECIPE")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECIPE")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "COOKING_TIME", nullable = false)
	private int cookingTime;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
	private List<Ingredient> listOfIngredients;

	@Column(name = "MEAL_TYPE", nullable = false)
	private String mealType;
}
