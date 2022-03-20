package com.yantracloud.yantracloudrefappwithcucumbertest.controller;

import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;
import com.yantracloud.yantracloudrefappwithcucumbertest.services.IProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ProductRestController {

    @Autowired
    IProductService entityService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/logging")
    @SecurityRequirement(name="bearerAuth")
    public ResponseEntity<String> logging() {
        return new ResponseEntity<>("logging/baeldung", HttpStatus.OK);
    }

    @PostMapping("/entity")
    @SecurityRequirement(name="bearerAuth")
    public ProductDto createEntity(@RequestBody ProductDto productDto) throws Exception {
        Product product = modelMapper.map(productDto, Product.class);
        Product productReturned = entityService.createEntity(product);
        return modelMapper.map(productReturned, ProductDto.class);
    }

    @GetMapping("/entity")
    @SecurityRequirement(name="bearerAuth")
    public List<ProductDto> getEntity() {
        List<Product> productReturned = entityService.getAllEntities();
        return productReturned
                .stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
    }
}
// curl --data "name=a&description=b&company=c" --request POST 'http://localhost:9096/entity'

//curl --data '{"name":"a","description":"b","company":"c"}' -H 'Content-Type: application/json' --request POST 'http://localhost:9096/api/entity'

//curl --data '{"name":"a","description":"b","company":"c"}' -H 'Content-Type: application/json' --request POST 'http://localhost:9097/api/entity'