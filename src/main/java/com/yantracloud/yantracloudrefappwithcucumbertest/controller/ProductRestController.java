package com.yantracloud.yantracloudrefappwithcucumbertest.controller;

import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.services.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    IEntityService entityService;

    @PostMapping("/entity")
    public ProductDto createEntity(@RequestBody ProductDto productDto){
        ProductDto productDto1 = entityService.createEntity(productDto);
        return productDto1;
    }
}
// curl --data "name=a&description=b&company=c" --request POST 'http://localhost:9096/entity'

//curl --data '{"name":"a","description":"b","company":"c"}' -H 'Content-Type: application/json' --request POST 'http://localhost:9096/api/entity'