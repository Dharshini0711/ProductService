package com.dharsh.productserviceapplication.Controllers;

import com.dharsh.productserviceapplication.Dtos.CreateProductDto;
import com.dharsh.productserviceapplication.Dtos.ErrorDto;
import com.dharsh.productserviceapplication.Dtos.FakeStoreProductDto;
import com.dharsh.productserviceapplication.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceapplication.Models.Product;
import com.dharsh.productserviceapplication.Services.FakeStoreProductService;
import com.dharsh.productserviceapplication.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);
        ResponseEntity<Product> responseEntity;
        if(p == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDto createProductDto){
        return productService.CreateProduct(createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getDescription(),
                createProductDto.getImage(),
                createProductDto.getCategory());
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProduct(id,fakeStoreProductDto );
    }



}
