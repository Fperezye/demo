package com.example.demo.infraestructure.pizzainfraestructure;
import java.util.UUID;
import java.util.List;

import com.example.demo.domain.pizzadomain.Pizza;
import com.example.demo.domain.pizzadomain.PizzaProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface PizzaJPARepository extends JpaRepository<Pizza, UUID> {

  @Query("SELECT i.id as id, i.name as name, i.price as price, i.image as image FROM Pizza i WHERE (:name is NULL OR name LIKE %:name%)")
  List<PizzaProjection> findByCriteria(@Param("name") String name, Pageable pageable);

  @Query("SELECT CASE WHEN COUNT(id)>0 THEN 1 ELSE 0 END FROM Pizza p WHERE p.name = :name")
  Integer existsByName(@Param("name") String name);
}
