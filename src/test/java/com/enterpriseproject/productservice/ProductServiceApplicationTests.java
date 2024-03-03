package com.enterpriseproject.productservice;

import com.enterpriseproject.productservice.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Commit
        //  Required as delete query needs to update the database
    void testQueries() {
        productRepository.findByTitleContaining("dipanshu");
        productRepository.deleteByTitle("dipanshu");
    }
}
