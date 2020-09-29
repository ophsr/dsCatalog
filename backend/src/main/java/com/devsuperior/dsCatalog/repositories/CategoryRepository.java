package com.devsuperior.dsCatalog.repositories;

import com.devsuperior.dsCatalog.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio do tipo Category e id Long
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
