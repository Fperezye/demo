package com.example.demo.application.userapplication;
import com.example.demo.domain.userdomain.Rol;
import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class UpdateDTO{
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String NewPassword;
    private Rol rol = Rol.USER;
    private String type;

}