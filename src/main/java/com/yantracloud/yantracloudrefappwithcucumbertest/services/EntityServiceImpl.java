package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EntityServiceImpl implements IEntityService {

    @Override
    public ProductDto createEntity(ProductDto productDto) {
        log.debug("Entity Object Received: " + productDto);
        return productDto;
    }

    @Override
    public void editEntity() {

    }

    @Override
    public void getSingleEntity() {

    }

    @Override
    public void getAllEntities() {

    }

    @Override
    public void deleteEntity() {

    }
}
