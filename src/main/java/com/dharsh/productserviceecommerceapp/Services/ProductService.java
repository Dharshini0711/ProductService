package com.dharsh.productserviceecommerceapp.Services;

import com.dharsh.productserviceecommerceapp.Dtos.CreateProductDto;
import com.dharsh.productserviceecommerceapp.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceecommerceapp.Models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(long id) throws ProductNotFoundException;

    Product createProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String Category);
}
