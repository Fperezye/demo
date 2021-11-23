package com.example.demo.application.userapplication;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class UserDTOIn {
    
    private String name;

    private BigDecimal price;
}
