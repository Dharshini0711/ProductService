package com.dharsh.productserviceecommerceapp;

import com.dharsh.productserviceecommerceapp.Projections.ProductProjection;
import com.dharsh.productserviceecommerceapp.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceECommerceAppApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }


    @Test
    void testingQueries(){

        List<ProductProjection> productProjections = productRepository.findTitleAndIdOfAllProductsWithCategoryName("Electronics");

        for(ProductProjection productProjection : productProjections){
            System.out.println(productProjection.getId());
            System.out.println(productProjection.getTitle());
        }
    }


}
