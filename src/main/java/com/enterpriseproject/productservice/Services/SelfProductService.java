package com.enterpriseproject.productservice.Services;

import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Category;
import com.enterpriseproject.productservice.Models.Product;
import com.enterpriseproject.productservice.Repositories.CategoryRepository;
import com.enterpriseproject.productservice.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Product> getAllProducts() {
//        List<Product> allProducts = productRepository.findAll();
        return productRepository.findAll();
    }


    @Override
    public Product getSingleProduct(Long id) throws ProductDoesNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductDoesNotExistException("Product with id: " + id + " does not exist");
        }

        return productOptional.get();
    }



    //  GET ALL PRODUCT CATEGORIES
    @Override
    public String[] getAllCategories() {
        List<String> categoryNames = categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());

        return categoryNames.toArray(new String[0]);
    }


    //  GET ALL PRODUCTS IN SINGLE CATEGORY
    @Override
    public List<Product> getInCategory(String category) {
        return productRepository.findByCategoryName(category);
    }


    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if (categoryOptional.isEmpty()) {
            product.setCategory(categoryRepository.save(product.getCategory()));
        } else {
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }


    //  PATCH CALL  :   PARTIAL UPDATE
    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) throw new RuntimeException();

        Product savedProduct = productOptional.get();

        if(product.getTitle() != null) savedProduct.setTitle(product.getTitle());
        if(product.getDescription() != null) savedProduct.setDescription(product.getDescription());
        if(product.getPrice() != null) savedProduct.setPrice(product.getPrice());
        if(product.getImageUrl() != null) savedProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(savedProduct);
    }

    //  PUT CALL    :   REPLACE PRODUCT
    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) throw new RuntimeException();

        Product savedProduct = productOptional.get();

        if(savedProduct.getTitle() != null) savedProduct.setTitle(product.getTitle());
        if(savedProduct.getDescription() != null) savedProduct.setDescription(product.getDescription());
        if(savedProduct.getPrice() != null) savedProduct.setPrice(product.getPrice());
        if(savedProduct.getImageUrl() != null) savedProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(savedProduct);
    }


    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) throw new RuntimeException();

        Product savedProduct = productOptional.get();

        if(!savedProduct.isDeleted()) savedProduct.setDeleted(true);
        productRepository.save(savedProduct);

        return savedProduct.isDeleted();
    }


}
