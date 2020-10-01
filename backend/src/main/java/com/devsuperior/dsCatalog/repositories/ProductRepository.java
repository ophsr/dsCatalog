package com.devsuperior.dsCatalog.repositories;

import com.devsuperior.dsCatalog.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
