package org.example;

import org.example.models.Product;
import org.example.repositories.ProductRepository;
import org.example.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UnitTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct() {
        String productName = "hotdog";
        Product product = productService.save(productName);
        assertNotNull(product);
        assertEquals(product.getName(), productName);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void deleteProduct() {
        Long id = 42L;
        Optional<Product> product = Optional.of(new Product(id,"hamburger", false));
        Mockito.doReturn(product).when(productRepository).findById(id);
        long deleteReturn = productService.delete(id);
        assertEquals(deleteReturn, id);
    }
}
