package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.repository.ProductRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URI;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefs {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;
    String body;

    @Value("${server.host}")
    private String server;

    ResultActions resultActions;

    ProductDto productDto;

    @Given("Client have new entity with details as below")
    public void client_have_new_entity_with_details_as_below(Map<String,String> map) throws JsonProcessingException {
        productDto = ProductDto.builder()
                .withName(map.get("name"))
                .withCompany(map.get("company"))
                .withDescription(map.get("description"))
                .build();
        body = objectMapper.writeValueAsString(productDto);
    }

    @When("Client calls end point {string} with method as 'post'")
    public void client_calls_end_point(String endPoint) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                post(URI.create(server + endPoint))
                .content(body)
                .contentType("application/json");

        resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
    }

    @Then("Client receive status code as {int}")
    public void client_receive_status_code_as(Integer int1) throws Exception {
        resultActions.andExpect(status().is(int1));
    }

    @Then("a new product is created in the DB")
    public void a_new_product_is_created_in_the_db() throws Exception {
        String nameFromDB = productRepository.findByName(productDto.getName()).getName();
        Assertions.assertThat(productDto.getName()).isEqualTo(nameFromDB);
    }


}
