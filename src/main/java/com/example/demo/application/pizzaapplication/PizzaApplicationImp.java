package com.example.demo.application.pizzaapplication;

import java.util.UUID;
import java.util.List;

import com.example.demo.application.ingredientapplication.IngredientApplicationImp;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.pizzadomain.Pizza;
import com.example.demo.domain.pizzadomain.PizzaWriteRepository;
import com.example.demo.domain.pizzadomain.PizzaReadRepository;
import com.example.demo.domain.pizzadomain.PizzaProjection;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication {

    private final PizzaWriteRepository pizzaWriteRepository;
    private final PizzaReadRepository pizzaReadRepository;
    private final IngredientApplicationImp ingredientApplicationImp;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaWriteRepository pizzaWriteRepository, final PizzaReadRepository pizzaReadRepository,
            final IngredientApplicationImp ingredientApplicationImp, final Logger logger) {

        super((id) -> pizzaWriteRepository.findById(id));

        this.pizzaReadRepository = pizzaReadRepository;
        this.pizzaWriteRepository = pizzaWriteRepository;
        this.ingredientApplicationImp = ingredientApplicationImp;
        this.logger = logger;
    }

    @Override
    public PizzaDTOOut add(PizzaDTOIn dto) {

        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        for (UUID ingredientId : dto.getIngredients()) {

            Ingredient ingredient = this.modelMapper.map(ingredientApplicationImp.get(ingredientId), Ingredient.class);
            pizza.addIngredient(ingredient);
        }
        pizza.setPrice(pizza.calculatePrice());
        pizza.setImage(pizza.getImage());
        pizza.validate("name", pizza.getName(), (name) -> this.pizzaWriteRepository.exists(name));

        this.pizzaWriteRepository.add(pizza);
        logger.info(this.serializeObject(pizza, "added"));

        return this.modelMapper.map(pizza, PizzaDTOOut.class);
    }

    @Override
    public PizzaDTOOut get(UUID id) {
        
        Pizza pizza = this.findById(id);
        return this.modelMapper.map(pizza, PizzaDTOOut.class);
    }

    @Override
    public void delete(UUID id) {
        Pizza pizza = this.findById(id);
        this.pizzaWriteRepository.delete(pizza);
        logger.info(this.serializeObject(pizza, "deleted"));
    }

    @Override
    public Page<PizzaProjection> getAll(String name, Pageable page) {
        return this.pizzaReadRepository.getAll(name, page);
    }

    protected String serializeObject(Pizza pizza, String messege){
        StringBuilder stringBuilder = new StringBuilder("Pizza {id: ").append(pizza.getId())
                                                                           .append(", name: ")
                                                                           .append(pizza.getName())
                                                                           .append(", price: ")
                                                                           .append(pizza.getPrice())
                                                                           .append("} ")
                                                                           .append(messege)
                                                                           .append(" successfully.");
        return stringBuilder.toString();
    }
}