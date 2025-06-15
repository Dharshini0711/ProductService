package com.dharsh.productserviceecommerceapp.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private String title;
    private String description;
    private String image;
    private String Category;
    private double price;
}
