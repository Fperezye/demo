package com.example.demo.domain.pizzadomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface PizzaProjection {

    public UUID getId();

    public String getName();

    public BigDecimal getPrice();

    public UUID getImage();
}