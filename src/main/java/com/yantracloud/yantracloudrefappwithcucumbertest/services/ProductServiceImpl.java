package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;
import com.yantracloud.yantracloudrefappwithcucumbertest.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createEntity(Product product) throws Exception {
        log.debug("Entity Object Received: " + product);
        //Find if there is a Product with this name

        Optional<Product> productIfPresent = Optional.ofNullable(productRepository.findByName(product.getName()));
        if (productIfPresent.isPresent()){
            throw new Exception("Entity Already present with this name. No Insert made.");
        }

        return productRepository.save(product);
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
