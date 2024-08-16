package com.example.Product.ProductData.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.Product.ProductData.Entities.Category;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
