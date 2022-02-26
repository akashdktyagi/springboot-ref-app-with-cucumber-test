package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;

public interface IEntityService {
    ProductDto createEntity(ProductDto productDto);
    void editEntity();
    void getSingleEntity();
    void getAllEntities();
    void deleteEntity();
}
