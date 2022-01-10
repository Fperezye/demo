package com.example.demo.infraestructure.ingredientrepository;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientdomain.Ingredient;
import com.example.demo.domain.ingredientdomain.IngredientProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientJPARepository extends JpaRepository<Ingredient, UUID>{
    
    @Query("SELECT i.id as id, i.name as name, i.price as price FROM Ingredient i WHERE (:name is NULL OR name LIKE %:name%)")
    List<IngredientProjection> findByCriteria(@Param("name") String name, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(i)>0 THEN 1 ELSE 0 END FROM Ingredient i WHERE i.name = :name")
    Integer existsByName(@Param("name") String name);
}
