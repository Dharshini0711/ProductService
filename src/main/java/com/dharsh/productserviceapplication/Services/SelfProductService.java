package com.dharsh.productserviceapplication.Services;

import com.dharsh.productserviceapplication.Dtos.FakeStoreProductDto;
import com.dharsh.productserviceapplication.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceapplication.Models.Category;
import com.dharsh.productserviceapplication.Models.Product;
import com.dharsh.productserviceapplication.Repositories.CategoryRepository;
import com.dharsh.productserviceapplication.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product CreateProduct(String title, double price, String description, String imageUrl, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        Category categoryFromDB = categoryRepository.findByTitle(category);
        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }
        else{
            product.setCategory(categoryFromDB);
        }

        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + "not found");
        }
        return product.get();
    }

    @Override
    public Product updateProduct(long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }
}
