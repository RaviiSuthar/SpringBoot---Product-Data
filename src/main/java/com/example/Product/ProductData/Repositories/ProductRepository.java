package com.example.Product.ProductData.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.Product.ProductData.Entities.Product;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product,Long>  {

}
