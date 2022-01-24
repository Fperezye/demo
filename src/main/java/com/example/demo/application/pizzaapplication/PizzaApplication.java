package com.example.demo.application.pizzaapplication;

import java.util.UUID;
import java.util.List;

import com.example.demo.domain.pizzadomain.PizzaProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface PizzaApplication {
    
    public PizzaDTOOut add(PizzaDTOIn dto);
    public PizzaDTOOut get(UUID id);
    public void delete(UUID id);
    public Page<PizzaProjection> getAll(String name,  Pageable page);
}