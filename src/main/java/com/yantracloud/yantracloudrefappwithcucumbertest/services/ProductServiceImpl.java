package com.yantracloud.yantracloudrefappwithcucumbertest.services;

import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;
import com.yantracloud.yantracloudrefappwithcucumbertest.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createEntity(Product product) throws Exception {
        Optional<Product> productIfPresent = Optional.ofNullable(productRepository.findByName(product.getName()));
        if (productIfPresent.isPresent()){
            throw new Exception("Entity Already present with this name: " + product.getName() + " No Insert made.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product editEntity(Product product) throws Exception {
        Optional<Product> productIfPresent = Optional.ofNullable(productRepository.findByName(product.getName()));
        if (productIfPresent.isPresent()){
            return productRepository.save(product);
        }else{
            throw new Exception("Entity not present with this name: " + product.getName() + "  can not Update.");
        }
    }

    @Override
    public void getSingleEntity() {

    }

    @Override
    public List<Product> getAllEntities() {
        return productRepository.findAll();
    }

    @Override
    public void deleteEntity() {

    }
}
