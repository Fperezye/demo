package com.example.demo.infraestructure.userinfraestructure;

import java.util.UUID;

import com.example.demo.domain.userdomain.User;
import com.example.demo.domain.userdomain.UserProjection;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserReactiveRepositoryImp  extends ReactiveCrudRepository<User, UUID> {

    @Query("SELECT i.id, i.name, i.price FROM users i WHERE (:name is NULL OR i.name LIKE concat('%', :name, '%')) limit :size offset :page")
    Flux<UserProjection> findByCriteria(@Param("name") String name, int size, int page);

    @Query("SELECT CASE WHEN COUNT(id)>0 THEN true ELSE false END FROM users WHERE name = :name")
    Mono<Boolean> existsByName(String name);

}