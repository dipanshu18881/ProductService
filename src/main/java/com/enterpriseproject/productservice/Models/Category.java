package com.enterpriseproject.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})   //  being already mapped in product class with an attribute called category
    private List<Product> products;
    private String name;
}

//  1     ->     1
// Product : Category
//  m     <-     1
// --------------------
//   m      :    1
