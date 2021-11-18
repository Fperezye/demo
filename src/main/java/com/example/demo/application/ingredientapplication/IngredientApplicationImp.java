package com.example.demo.application.ingredientapplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.ingredientdomain.IngredientRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class IngredientApplicationImp extends ApplicationBase<Ingredient, UUID> implements IngredientApplication{

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IngredientApplicationImp (final IngredientRepository ingredientRepository, final ModelMapper modelMapper){
        super((id) -> ingredientRepository.findById(id));

        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<IngredientDTOOut> add(IngredientDTOIn ingredientDTOIn) {
        Ingredient ingredient = modelMapper.map(ingredientDTOIn, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        ingredient.setThisNew(true);
        return this.ingredientRepository.add(ingredient).flatMap(entity -> Mono.just(this.modelMapper.map(entity, IngredientDTOOut.class)));
    }

    @Override
    public Mono<IngredientDTOOut> get(UUID id) {

        return this.findById(id).flatMap( dbingredient -> Mono.just(this.modelMapper.map(dbingredient, IngredientDTOOut.class)));
    }
}
