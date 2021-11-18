package com.example.demo.controller.ingredientcontroller;

import java.util.UUID;

import com.example.demo.application.ingredientapplication.IngredientApplication;
import com.example.demo.application.ingredientapplication.IngredientDTOIn;
import com.example.demo.application.ingredientapplication.IngredientDTOOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientApplication ingredientApplication;

    @Autowired
    public IngredientController(final IngredientApplication ingredientApplication){
        this.ingredientApplication = ingredientApplication;
    } 
      
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody Mono<IngredientDTOOut> add(@RequestBody IngredientDTOIn ingredientDTOIn) {
        return this.ingredientApplication.add(ingredientDTOIn);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public Mono<ResponseEntity<IngredientDTOOut>> get(@PathVariable UUID id) {
        Mono<IngredientDTOOut> ingredientDTOOut = this.ingredientApplication.get(id);
        return ingredientDTOOut.map(ingredient -> ResponseEntity.ok(ingredient)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
