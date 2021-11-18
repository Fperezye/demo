package com.example.demo.infraestructure.ingredientinfraestructure;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.ingredientdomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class IngredientRepositoryImp implements IngredientRepository{

    private final IngredientReactiveRepository ingredientReactiveRepository;

    @Autowired
    public IngredientRepositoryImp(IngredientReactiveRepository ingredientReactiveRepository){
        this.ingredientReactiveRepository = ingredientReactiveRepository;
    }

    @Override
    public Mono<Ingredient> add(Ingredient ingredient) {
        return this.ingredientReactiveRepository.save(ingredient);
    }

    @Override
    public Mono<Ingredient> findById(UUID id) {
        return this.ingredientReactiveRepository.findById(id);
    }

    @Override
    public Mono<Ingredient> update(Ingredient ingredient) {
        return this.ingredientReactiveRepository.save(ingredient);
    }
    
    @Override
    public Mono<Void> delete(Ingredient ingredient) {
        return this.ingredientReactiveRepository.delete(ingredient);
    }
}
