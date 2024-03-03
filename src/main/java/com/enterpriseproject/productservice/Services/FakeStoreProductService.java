package com.enterpriseproject.productservice.Services;

import com.enterpriseproject.productservice.DTOs.FakeStoreProductDto;
import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import com.enterpriseproject.productservice.Models.Category;
import com.enterpriseproject.productservice.Models.Product;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //To convert Third party product type using Product DTO to Our Product type
    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        //product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());

        return product;
    }

    //  GET CALL : SINGLE PRODUCT
    @Override
    public Product getSingleProduct(Long id) throws ProductDoesNotExistException {

        // Never throw exception like this as it leaves stack trace to frontend
//        throw new RuntimeException("Throw new exception");
        // This exception is caught by ControllerAdvices
//        int error = 1/0;

        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if(productDto == null) {
            throw new ProductDoesNotExistException("Product with id: '" + id + "' does not exist.");
        }
        return convertFakeStoreProductToProduct(productDto);
    }

    //  GET CALL : ALL PRODUCTS
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> allProducts = new ArrayList<>();

        for(FakeStoreProductDto dto: response) {
            allProducts.add(convertFakeStoreProductToProduct(dto));
        }

        return allProducts;
    }

    //  POST CALL : ADD NEW PRODUCT
//    @Override
//    public Product addNewProduct(FakeStoreProductDto productDto) {
//        FakeStoreProductDto Dto = restTemplate.postForObject(
//                "https://fakestoreapi.com/products",
//                productDto,
//                FakeStoreProductDto.class
//        );
//        return convertFakeStoreProductToProduct(Dto);
//    }

    //  GET CALL : GET ALL CATEGORIES
    @Override
    public String[] getAllCategories() {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
    }

    //  GET CALL : GET SINGLE CATEGORY
    @Override
    public List<Product> getInCategory(String category) {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDto[].class
        );

        List<Product> allProducts = new ArrayList<>();

        for(FakeStoreProductDto dto: response) {
            allProducts.add(convertFakeStoreProductToProduct(dto));
        }

        return allProducts;
    }

    //  PATCH CALL : PARTIAL UPDATE
    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    //  PUT CALL : REPLACE PRODUCT
    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDto(), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }


    //  DELETE CALL : DELETE SINGLE PRODUCT
    @Override
    public Boolean deleteProduct(Long id) {
       return null;
    }
}
