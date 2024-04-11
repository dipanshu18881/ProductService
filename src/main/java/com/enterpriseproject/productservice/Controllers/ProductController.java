package com.enterpriseproject.productservice.Controllers;

import com.enterpriseproject.productservice.Commons.AuthenticationCommons;
import com.enterpriseproject.productservice.DTOs.Role;
import com.enterpriseproject.productservice.DTOs.UserDto;
import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Product;
import com.enterpriseproject.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    //  Get Call : Get all products
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {

//        UserDto userDto = authenticationCommons.validateToken(token);

//        if(userDto == null) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        boolean isAdmin = false;
//
//        for(Role role: userDto.getRoles()) {
//            if(role.getName().equals("ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
//
//        if(!isAdmin) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


//        //  Validate token for : " .../products "
//        if(authenticationCommons.validateToken(token) == null) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        return new ResponseEntity<>(
//                productService.getAllProducts(), HttpStatus.OK
//        );

        List<Product> products = productService.getAllProducts(); // o p q

        List<Product> finalProducts = new ArrayList<>();

        for (Product p: products) { // o p q
            p.setTitle(p.getTitle());
            finalProducts.add(p);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                finalProducts, HttpStatus.OK
        );
        return response;
    }

    //  Get Call : Get all products in a particular category
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductDoesNotExistException {
        return new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
    }

    //  Post Call : Add new product
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.CREATED
        );
    }

    //  Patch Call : Update product with new values
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(
                productService.updateProduct(id, product),
                HttpStatus.ACCEPTED
        );
    }

    //  Put Call : Replace product with new one
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new ResponseEntity<>(
                productService.replaceProduct(id, product),
                HttpStatus.ACCEPTED
        );
    }

    //  Delete Call : Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
