package com.enterpriseproject.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private Double price;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;
    private String description;
    private String imageUrl;
//    private int numberOfSales;
}

//  1     ->     1
// Product : Category
//  m     <-     1
// --------------------
//   m      :    1
