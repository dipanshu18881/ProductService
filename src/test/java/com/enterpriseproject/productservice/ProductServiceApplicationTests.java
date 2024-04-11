package com.enterpriseproject.productservice;

import com.enterpriseproject.productservice.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

//@SpringBootTest
class ProductServiceApplicationTests {
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
////    public ProductServiceApplication(ProductRepository productRepository) {
////        this.productRepository = productRepository;
////    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    void testQueries() {
//        productRepository.findByTitleContaining("naman");
////        productRepository.deleteByTitle("naman");
//
//        List<ProductWithIdAndTitle> products = productRepository.somethingsomething(102L);
//
//        for (ProductWithIdAndTitle product: products) {
//            System.out.println(product.getId());
//            System.out.println(product.getTitle());
//        }
//
//        List<ProductWithIdAndTitle> products1 = productRepository.somesome2(102L);
//
//        Optional<Category> category = categoryRepository.findById(52L);
//
////        ..................
//    /// .....................
//        // ..................
//        // .....
//        if (true) {
//            System.out.println("Getting Products");
//            Category category1 = category.get();
//            List<Product> products2 = category1.getProducts();
//            products2.get(0).getImageUrl();
//        }
//    }


}
