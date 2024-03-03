package com.enterpriseproject.productservice.Services;

import com.enterpriseproject.productservice.DTOs.FakeStoreProductDto;
import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id) throws ProductDoesNotExistException;

    //  POST CALL : ADD NEW PRODUCT
//    Product addNewProduct(FakeStoreProductDto productDto);

    String[] getAllCategories();
    List<Product> getInCategory(String category);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Product addNewProduct(Product product);
    Boolean deleteProduct(Long id);

}
