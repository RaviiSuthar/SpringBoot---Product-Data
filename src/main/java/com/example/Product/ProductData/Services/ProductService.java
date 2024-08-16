package com.example.Product.ProductData.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Product.ProductData.Entities.Product;
import com.example.Product.ProductData.Repositories.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(int page) {

        // im keeping page size as 5
        Page<Product> allProducts = productRepository.findAll(PageRequest.of(page, 5));

        for (Product product : allProducts.getContent()) {
            product.getCategory().setProducts(null);
        }
        return allProducts;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        product.getCategory().setProducts(null);
        return product;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> {

                    product.getCategory().setProducts(null);

                    return product;
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setCategory(productDetails.getCategory());
                    productRepository.save(product);

                    product.getCategory().setProducts(null);
                    return product;
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public Product deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    product.getCategory().setProducts(null);
                    return product;
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}
