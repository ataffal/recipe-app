package com.recipe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recipe.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	Optional<Recipe> findByName(String name);

	@Query("SELECT r FROM Recipe r " + "WHERE (:name IS NULL OR r.name LIKE %:name%) "
			+ "AND (:mealtype IS NULL OR r.mealType = :mealtype)")
	List<Recipe> findByAttributes(@Param("name") String name, @Param("mealtype") String mealtype);
}
