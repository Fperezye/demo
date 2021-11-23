package com.example.demo.domain.userdomain;

import reactor.core.publisher.Flux;

public interface UserReadRepository {
    
    public Flux<UserProjection> getAll(String name, int page, int size);
}