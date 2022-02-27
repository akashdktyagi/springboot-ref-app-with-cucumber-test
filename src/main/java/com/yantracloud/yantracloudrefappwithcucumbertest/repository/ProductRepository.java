package com.yantracloud.yantracloudrefappwithcucumbertest.repository;

import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByName(String name);
}
