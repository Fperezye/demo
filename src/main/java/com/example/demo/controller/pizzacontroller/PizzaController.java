package com.example.demo.controller.pizzacontroller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.pizzaapplication.PizzaDTOOut;
import com.example.demo.application.pizzaapplication.PizzaApplication;
import com.example.demo.application.pizzaapplication.PizzaDTOIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaApplication pizzaApplication;

    @Autowired
    public PizzaController(PizzaApplication pizzaApplication){
        this.pizzaApplication = pizzaApplication;
    }

    @CrossOrigin("http://localhost:4200/")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody final PizzaDTOIn dto){
        PizzaDTOOut pizzaDTOOut = this.pizzaApplication.add(dto);

        return ResponseEntity.status(201).body(pizzaDTOOut);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        PizzaDTOOut pizzaDTOOut = this.pizzaApplication.get(id);
        return ResponseEntity.ok(pizzaDTOOut);
    }

    @CrossOrigin("http://localhost:4200/")
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable UUID id) {
        this.pizzaApplication.delete(id);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
        @RequestParam(required = false) String name,
        Pageable page
    ){
        return ResponseEntity.status(200).body(this.pizzaApplication.getAll(name, page));
    }
}