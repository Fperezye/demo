package com.example.demo.application.ingredientapplication;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.IngredientProjection;

import java.util.List;


public interface IngredientApplication {
    
    public IngredientDTOOut add(IngredientDTOIn dto);
    public IngredientDTOOut get(UUID id);
    public void update(UUID id, IngredientDTOIn dtos);
    public void delete(UUID id);
    public List<IngredientProjection> getAll(String name,  int page, int size);
}