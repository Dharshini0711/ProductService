package com.dharsh.productserviceecommerceapp.Repositories;

import com.dharsh.productserviceecommerceapp.Models.Product;
import com.dharsh.productserviceecommerceapp.Projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    //In declared queries u ll only get object , if we want to get a specific columns we need to use HQL

    @Query("select p.title as titleName , p.id as id from Product as p where p.category.title = :categoryName")
    List<ProductProjection> findTitleAndIdOfAllProductsWithCategoryName(@Param("categoryName") String categoryName);



}
