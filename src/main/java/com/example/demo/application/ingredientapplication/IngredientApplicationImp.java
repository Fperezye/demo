package com.example.demo.application.ingredientapplication;

import java.util.UUID;

import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.ingredientdomain.IngredientRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class IngredientApplicationImp implements IngredientApplication{

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IngredientApplicationImp (final IngredientRepository ingredientRepository, final ModelMapper modelMapper){
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<IngredientDTOOut> add(IngredientDTOIn dto) {
        Ingredient add = modelMapper.map(dto, Ingredient.class);
        add.setId(UUID.randomUUID());
        add.setThisNew(true);
        return this.ingredientRepository.add(add).flatMap(ingredient -> Mono.just(this.modelMapper.map(ingredient, IngredientDTOOut.class)));
    }
}
