package com.example.demo.domain.ingredientdomain;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.FindById;

import reactor.core.publisher.Mono;

public interface IngredientRepository extends FindById<Ingredient, UUID> {
    public Mono<Ingredient> add(Ingredient ingredient);
    public Mono<Ingredient> update(Ingredient ingredient);
    public Mono<Void> delete(Ingredient ingredient);
}
