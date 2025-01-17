package com.dharsh.productserviceapplication.Repositories;

import com.dharsh.productserviceapplication.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
     Category findByTitle(String title); //JPA method
}
