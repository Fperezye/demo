package com.example.demo.domain.ingredientdomain;

import java.util.List;

public interface IngredientReadRepository {
    
    public List<IngredientProjection> getAll(String name, int page, int size);
}