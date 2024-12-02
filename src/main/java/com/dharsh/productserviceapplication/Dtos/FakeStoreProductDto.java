package com.dharsh.productserviceapplication.Dtos;

import com.dharsh.productserviceapplication.Models.Category;
import com.dharsh.productserviceapplication.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);
        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);
        return product;
    }

}
