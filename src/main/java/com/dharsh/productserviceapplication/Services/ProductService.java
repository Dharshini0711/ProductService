package com.dharsh.productserviceapplication.Services;

import com.dharsh.productserviceapplication.Dtos.CreateProductDto;
import com.dharsh.productserviceapplication.Dtos.FakeStoreProductDto;
import com.dharsh.productserviceapplication.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceapplication.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService  {
    List<Product> getAllProducts();

    Product CreateProduct(String title, double price, String description, String imageUrl,
                          String category);

    Product getSingleProduct(long id) throws ProductNotFoundException;

    Product updateProduct(long id, FakeStoreProductDto fakeStoreProductDto);


}
