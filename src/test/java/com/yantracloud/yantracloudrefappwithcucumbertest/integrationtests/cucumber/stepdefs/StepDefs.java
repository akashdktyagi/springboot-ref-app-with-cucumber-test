package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantracloud.yantracloudrefappwithcucumbertest.model.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URI;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefs {
//    resultActions = mockMvc.perform(post(createEndPoint, 42L)
//                .contentType("application/json")
//                .header("Authorization","Bearer " +  token)
//                .content(objectMapper.writeValueAsString(product)))
//            .andExpect(status().is(200));

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    String body;
    private String server = "http://localhost:9096/api";
    ResultActions resultActions;

    @Given("Client have new entity with details as below")
    public void client_have_new_entity_with_details_as_below(Map<String,String> map) throws JsonProcessingException {
        Product product = Product.builder()
                .withName(map.get("name"))
                .withCompany(map.get("company"))
                .withDescription(map.get("description"))
                .build();
        body = objectMapper.writeValueAsString(product);
    }

    @When("Client calls end point {string} with method as {string}")
    public void client_calls_end_point(String endPoint, String method) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                request(method, URI.create(server + endPoint))
                .content(body);
        resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
    }


    @Then("Client receive status code as {int}")
    public void client_receive_status_code_as(Integer int1) throws Exception {
        resultActions.andExpect(status().is(int1));
    }


}
