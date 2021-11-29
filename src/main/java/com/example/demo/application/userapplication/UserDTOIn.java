package com.example.demo.application.userapplication;

import java.util.UUID;

import com.example.demo.domain.userdomain.Rol;
import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class UserDTOIn {
    
    private UUID id;
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String type;
    private Rol rol;
    private String token;
}
