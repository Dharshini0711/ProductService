package com.dharsh.productserviceecommerceapp.Services;

import com.dharsh.productserviceecommerceapp.Dtos.CreateProductDto;
import com.dharsh.productserviceecommerceapp.Dtos.FakeStoreProductDto;
import com.dharsh.productserviceecommerceapp.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceecommerceapp.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate; // call the 3rd part api


    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("'https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();
            products.add(p);
        }
        return products;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        //FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("'https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDtoResponse = fakeStoreProductDto.getBody();

        if(fakeStoreProductDtoResponse == null){
            throw new ProductNotFoundException("Product with id "+id + " not found");
        }
        return fakeStoreProductDtoResponse.toProduct();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String imageUrl,
                                 String Category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(Category);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return fakeStoreProductDto1.toProduct();
    }

    @Override
    public Page<Product> getAllProductsPaginated(int page, int size) {
        return null;
    }
}
