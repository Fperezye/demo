package com.example.demo.infraestructure.ingredientrepository;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.Ingredient;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientReactiveRepository extends ReactiveCrudRepository<Ingredient, UUID> {

}