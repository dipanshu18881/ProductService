package com.enterpriseproject.productservice.Services;

import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Category;
import com.enterpriseproject.productservice.Models.Product;
import com.enterpriseproject.productservice.Repositories.CategoryRepository;
import com.enterpriseproject.productservice.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(Category::getName).toList().toArray(new String[0]);
    }


    //  GET ALL PRODUCTS IN SINGLE CATEGORY
    @Override
    public List<Product> getInCategory(String category) {
        return productRepository.findByCategoryName(category);
    }


    @Override
    public Product addNewProduct(Product product) {
        // check if category exists or not if not then save it with same name and id
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty()){
//            product.setCategory(categoryRepository.save(product.getCategory()));
            // Here we have used cascade type persist in category entity, so we don't need to save category explicitly
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

        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setImageUrl(product.getImageUrl());

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
