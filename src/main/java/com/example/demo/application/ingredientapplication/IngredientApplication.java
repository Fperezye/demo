package com.example.demo.application.ingredientapplication;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.IngredientProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IngredientApplication {
    
    public IngredientDTOOut add(IngredientDTOIn dto);
    public IngredientDTOOut get(UUID id);
    public void update(UUID id, IngredientDTOIn dtos);
    public void delete(UUID id);
    public Page<IngredientProjection> getAll(String name,  Pageable page);
}