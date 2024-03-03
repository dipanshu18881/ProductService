package com.enterpriseproject.productservice.Controllers;

import com.enterpriseproject.productservice.Models.Product;
import com.enterpriseproject.productservice.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private ProductService productService;

    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(
                productService.getAllCategories(),
                HttpStatus.OK);
    }

    @GetMapping("/categories/{String}")
    public ResponseEntity<List<Product>> getInCategory(@PathVariable( "String") String category) {
        return new ResponseEntity<>(
                productService.getInCategory(category),
                HttpStatus.OK);
    }
}
