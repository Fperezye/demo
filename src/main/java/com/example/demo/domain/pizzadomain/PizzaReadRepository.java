package com.example.demo.domain.pizzadomain;

import java.util.List;

public interface PizzaReadRepository {
    
    public List<PizzaProjection> getAll(String name, int page, int size);
}