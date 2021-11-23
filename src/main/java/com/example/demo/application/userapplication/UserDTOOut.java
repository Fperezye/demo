package com.example.demo.application.userapplication;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class UserDTOOut{
    
    private UUID id;
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    
}