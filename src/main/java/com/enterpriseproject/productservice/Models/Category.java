package com.enterpriseproject.productservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> products;
    private String name;
}

//  1     ->     m
// Category : Product
//  1     <-     1
// --------------------
//   1      :    m