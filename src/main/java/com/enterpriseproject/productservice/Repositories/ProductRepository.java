package com.enterpriseproject.productservice.Repositories;

import com.enterpriseproject.productservice.Models.Category;
import com.enterpriseproject.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String word);
    long deleteByTitle(String title);
    List<Product> findByTitleAndDescription(String title, String description);
    Product findByIdAndCategoryOrderByTitle(Long id, Category category);
    List<Product> findByCategory_Id(Long id);
    Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> findAll();
    List<Product> findByCategoryName(String category);


}
