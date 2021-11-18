package com.example.demo.infraestructure.ingredientinfraestructure;

import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.ingredientdomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class IngredientRepositoryImp implements IngredientRepository{

    private final IngredientJPARepository ingredientJPARepository;

    @Autowired
    public IngredientRepositoryImp(IngredientJPARepository ingredientJPARepository){
        this.ingredientJPARepository = ingredientJPARepository;
    }

    @Override
    public Mono<Ingredient> add(Ingredient ingredient) {
        return this.ingredientJPARepository.save(ingredient);
    }
    
}
