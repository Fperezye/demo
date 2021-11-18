package com.example.demo.infraestructure.ingredientinfraestructure;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.Ingredient;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientJPARepository  extends ReactiveCrudRepository<Ingredient, UUID> {

}