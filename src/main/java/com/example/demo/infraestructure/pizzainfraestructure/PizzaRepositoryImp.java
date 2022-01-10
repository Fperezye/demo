package com.example.demo.infraestructure.pizzainfraestructure;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import com.example.demo.domain.pizzadomain.Pizza;
import com.example.demo.domain.pizzadomain.PizzaWriteRepository;
import com.example.demo.domain.pizzadomain.PizzaReadRepository;
import com.example.demo.domain.pizzadomain.PizzaProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepositoryImp implements PizzaWriteRepository, PizzaReadRepository {

    private PizzaJPARepository pizzaJPARepository;

    @Autowired
    public PizzaRepositoryImp(PizzaJPARepository pizzaJPARepository){
        this.pizzaJPARepository = pizzaJPARepository;
    }

    @Override
    public void add(Pizza pizza) {
        this.pizzaJPARepository.save(pizza);
    }

    @Override
    public Optional<Pizza> findById(UUID id) {
        return this.pizzaJPARepository.findById(id);
    }

    @Override
    public void delete(Pizza pizza) {
        this.pizzaJPARepository.delete(pizza);
    }

    @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaJPARepository.findByCriteria(name,
        PageRequest.of(page, size));
    }


    @Override
    public Integer exists(String name) {
        return this.pizzaJPARepository.existsByName(name);
    }
}
