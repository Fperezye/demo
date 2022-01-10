package com.example.demo.application.pizzaapplication;

import java.util.UUID;
import java.util.List;

import com.example.demo.domain.pizzadomain.PizzaProjection;

public interface PizzaApplication {
    
    public PizzaDTOOut add(PizzaDTOIn dto);
    public PizzaDTOOut get(UUID id);
    public void delete(UUID id);
    public List<PizzaProjection> getAll(String name,  int page, int size);
}