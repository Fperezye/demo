package com.example.demo.application.userapplication;

import java.util.UUID;

import com.example.demo.domain.userdomain.UserProjection;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserApplication {
    public Mono<UserDTOOut> add(UserDTOIn ingredientDTOIn);
    public Mono<UserDTOOut> get(UUID id);
    public Mono<UserDTOOut> update(UUID id, UserDTOIn ingredientDTOIn);
    public Mono<UserDTOOut> delete(UUID id);
    public Flux<UserProjection> getAll(String name,  int page, int size);
}
