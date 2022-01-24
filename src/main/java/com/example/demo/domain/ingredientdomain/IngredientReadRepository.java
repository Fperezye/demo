package com.example.demo.domain.ingredientdomain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientReadRepository {
    
    public Page<IngredientProjection> getAll(String name, Pageable page);
}