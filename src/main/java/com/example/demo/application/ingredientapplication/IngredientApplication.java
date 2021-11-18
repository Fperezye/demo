package com.example.demo.application.ingredientapplication;

import reactor.core.publisher.Mono;

public interface IngredientApplication {
    public Mono<IngredientDTOOut> add(IngredientDTOIn ingredientDTOIn);
}
