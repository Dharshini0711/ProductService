package com.dharsh.productserviceecommerceapp.Services;

import com.dharsh.productserviceecommerceapp.Exceptions.ProductNotFoundException;
import com.dharsh.productserviceecommerceapp.Models.Category;
import com.dharsh.productserviceecommerceapp.Models.Product;
import com.dharsh.productserviceecommerceapp.Repositories.CategoryRepository;
import com.dharsh.productserviceecommerceapp.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

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

    public Page<Product> getAllProductsPaginated(int pageNo, int pageSize){
        return productRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("title")));
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return p.get();
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        Category categoryFromDB = categoryRepository.findByTitle(category);
        if(categoryFromDB == null) {
            Category category2 = new Category();
            category2.setTitle(category);
            product.setCategory(category2);
        }
        product.setCategory(categoryFromDB);

        return productRepository.save(product);
    }
}
