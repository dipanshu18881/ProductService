package com.enterpriseproject.productservice.Controllers;

import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Product;
import com.enterpriseproject.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductDoesNotExistException {
        return new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(
                productService.updateProduct(id, product),
                HttpStatus.ACCEPTED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(
                productService.replaceProduct(id, product),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
