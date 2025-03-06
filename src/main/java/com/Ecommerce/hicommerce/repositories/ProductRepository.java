package com.Ecommerce.hicommerce.repositories;


import com.Ecommerce.hicommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
}
