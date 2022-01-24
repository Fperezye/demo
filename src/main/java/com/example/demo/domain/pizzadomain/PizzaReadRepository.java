package com.example.demo.domain.pizzadomain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface PizzaReadRepository {
    
    public Page<PizzaProjection> getAll(String name, Pageable page);
}