package com.dharsh.productserviceapplication.Services;

import com.dharsh.productserviceapplication.Dtos.CreateProductDto;
import com.dharsh.productserviceapplication.Dtos.FakeStoreProductDto;
import com.dharsh.productserviceapplication.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceapplication.Models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate; // using this we will be able to call third party api's
    private RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();
            products.add(p);
        }
        return products;
    }

    @Override
    public Product CreateProduct(String title, double price, String description, String imageUrl,
                                 String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return fakeStoreProductDto1.toProduct();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException{
       /*FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();*/

        //Check for data in cache
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "product "+id);

        if(product!=null){
            //CACHEHIT
            return product;
        }


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoEntity.getBody();

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with "+ id + "is not present");
        }
        product =  fakeStoreProductDto.toProduct();
        redisTemplate.opsForHash().put("PRODUCTS", "product "+id, product);
        return product;
    }

    @Override
    public Product updateProduct(long id, FakeStoreProductDto fakeStoreProductDto ) {
        restTemplate.put("https://fakestoreapi.com/products/" + id, fakeStoreProductDto);
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto1.toProduct();
    }
}
