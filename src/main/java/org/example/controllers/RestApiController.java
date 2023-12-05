package org.example.controllers;


import net.minidev.json.JSONObject;
import org.example.models.Product;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class RestApiController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    private Iterable<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PostMapping("/update/{id}")
    private void update(@PathVariable("id") Long id) {
        var product = productService.findById(id);
        productService.update(id, product.get().getName(), !product.get().isBought());
    }

    @PostMapping("/add")
    private ResponseEntity<String> addProduct(@RequestBody JSONObject jsonProduct) {
        var name = jsonProduct.get("productName").toString();
        productService.save(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
