package com.example.demo.domain.ingredientdomain;

import reactor.core.publisher.Mono;

public interface IngredientRepository {
    public Mono<Ingredient> add(Ingredient ingredient);
}
