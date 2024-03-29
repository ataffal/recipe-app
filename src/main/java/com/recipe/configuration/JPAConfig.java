package com.recipe.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.recipe.repository")
@EntityScan(basePackages = "com.recipe.model")
public class JPAConfig {

}
