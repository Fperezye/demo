package com.example.demo.application.ingredientapplication;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface IngredientApplication {
    public Mono<IngredientDTOOut> add(IngredientDTOIn ingredientDTOIn);
    public Mono<IngredientDTOOut> get(UUID id);
    public Mono<IngredientDTOOut> update(UUID id, IngredientDTOIn ingredientDTOIn);
    public Mono<IngredientDTOOut> delete(UUID id);
}
