package com.enterpriseproject.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
}

//  1     ->     1
// Product : Category
//  m     <-     1
// --------------------
//   m      :    1
