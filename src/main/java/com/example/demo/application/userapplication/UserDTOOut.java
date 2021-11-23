package com.example.demo.application.userapplication;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class UserDTOOut{
    
    private UUID id;
    
    private String name;

    private BigDecimal price;

    
}