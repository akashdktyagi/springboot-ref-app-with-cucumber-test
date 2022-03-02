package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;

import java.util.List;

public interface IProductService {
    Product createEntity(Product product) throws Exception;
    void editEntity();
    void getSingleEntity();
    List<Product> getAllEntities();
    void deleteEntity();
}
