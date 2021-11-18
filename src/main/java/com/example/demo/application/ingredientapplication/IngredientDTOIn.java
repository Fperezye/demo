package com.example.demo.application.ingredientapplication;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class IngredientDTOIn {
    
    private String name;

    private BigDecimal price;
}
