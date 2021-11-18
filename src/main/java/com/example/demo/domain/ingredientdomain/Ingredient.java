package com.example.demo.domain.ingredientdomain;

import java.math.BigDecimal;

import com.example.demo.core.EntityBase;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("Ingredients")
public @NoArgsConstructor @Getter @Setter class Ingredient extends EntityBase{

    @Column("name")
    private String name;

    @Column("price")
    private BigDecimal price;

    @Override
    public String toString() {

        return String.format("Ingredient {id: %s, name: %s, price: %s}", this.getId(), this.getName(), this.getPrice());
    }

    @Override
    public boolean isNew() {
        return this.isThisNew();
    }

}
