package com.recipe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.recipe" })
public class RecipeSpringapp1Application {

	public static void main(String[] args) {
		SpringApplication.run(RecipeSpringapp1Application.class, args);
	}

}
