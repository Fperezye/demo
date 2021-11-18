package com.example.demo.controller.ingredientcontroller;

import com.example.demo.application.ingredientapplication.IngredientApplication;
import com.example.demo.application.ingredientapplication.IngredientDTOIn;
import com.example.demo.application.ingredientapplication.IngredientDTOOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /*@GetMapping("/{id}")
    Mono<ResponseEntity<Ingredient>> getIngredient(@PathVariable UUID id) {                
        return ingredientRepository.findById(id)
        .switchIfEmpty(
          Mono.error(new RuntimeException())
        )
        .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK));
        
    }*/

    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Mono<IngredientDTOOut> add(@RequestBody IngredientDTOIn ingredientDTOIn) {
        Mono<IngredientDTOOut> ingredientDTOOut = this.ingredientApplication.add(ingredientDTOIn);
        return ingredientDTOOut;
    }
    /*
    @DeleteMapping("/{studentID}")
    Mono<ResponseEntity<Ingredient>> deleteStudent(@PathVariable UUID id) {
        return ingredientRepository.findById(id).switchIfEmpty(
            Mono.error(new RuntimeException())
          )
        .map(deletedIngredient -> new ResponseEntity<>(deletedIngredient, HttpStatus.CREATED)
        );
    }*/
}
