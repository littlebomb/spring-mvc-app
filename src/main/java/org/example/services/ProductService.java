package org.example.services;

import org.example.models.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product save(String name){
        Product product = new Product(name);
        productRepository.save(product);
        return product;
    }

    public Product update(Long id, String name, boolean isBought){
        Product product = new Product(id, name);
        product.setBought(isBought);
        productRepository.save(product);
        return product;
    }

    public long delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return product.getId();

    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }
}
