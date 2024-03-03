package com.enterpriseproject.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double Price;
    private String category;
    private String description;
    private String image;
}
