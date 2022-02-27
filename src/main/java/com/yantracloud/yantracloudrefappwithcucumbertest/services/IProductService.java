package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;

public interface IProductService {
    Product createEntity(Product product) throws Exception;
    void editEntity();
    void getSingleEntity();
    void getAllEntities();
    void deleteEntity();
}
