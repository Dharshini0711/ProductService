package com.dharsh.productserviceecommerceapp.Controllers;

import com.dharsh.productserviceecommerceapp.Dtos.CreateProductDto;
import com.dharsh.productserviceecommerceapp.Dtos.ErrorDto;
import com.dharsh.productserviceecommerceapp.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceecommerceapp.Models.Product;
import com.dharsh.productserviceecommerceapp.Services.ProductService;
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
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product p = productService.getProductById(id);
        ResponseEntity<Product> response;

        if(p == null){
            response = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        }
        else{
            response = new ResponseEntity<>(p, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto.getTitle(),
                createProductDto.getDescription(), createProductDto.getPrice(),
                createProductDto.getImage(), createProductDto.getCategory());
    }

    /*@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> HandleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return responseEntity;

    }*/
}
