package com.example.demo.controller.ingredientcontroller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.ingredientapplication.IngredientDTOIn;
import com.example.demo.application.ingredientapplication.IngredientApplication;
import com.example.demo.application.ingredientapplication.IngredientDTOOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ingredients")
public class IngredientController{
    
    private final IngredientApplication ingredientApplication;

    @Autowired
    public IngredientController(IngredientApplication ingredientApplication){
        this.ingredientApplication = ingredientApplication;
    }

    @CrossOrigin("http://localhost:4200/")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody IngredientDTOIn dto){
        IngredientDTOOut ingredientDTO = this.ingredientApplication.add(dto);

        return ResponseEntity.status(201).body(ingredientDTO);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        IngredientDTOOut ingredientDTO = this.ingredientApplication.get(id);
        return ResponseEntity.ok(ingredientDTO);
    }

    @CrossOrigin("http://localhost:4200/")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody IngredientDTOIn dto) {
        this.ingredientApplication.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin("http://localhost:4200/")
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable UUID id) {
        this.ingredientApplication.delete(id);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
        @RequestParam(required = false) String name,
        Pageable page
    ){
        return ResponseEntity.status(200).body(this.ingredientApplication.getAll(name, page));
    }
}