package com.example.demo.domain.ingredientdomain;

import java.math.BigDecimal;

import com.example.demo.core.EntityBase;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("ingredients")
public @NoArgsConstructor @Getter @Setter class Ingredient extends EntityBase{

    @Column("name")
    private String name;

    @Column("price")
    private BigDecimal price;

}
