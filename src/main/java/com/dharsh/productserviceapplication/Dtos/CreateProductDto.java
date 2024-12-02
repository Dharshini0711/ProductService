package com.dharsh.productserviceapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
