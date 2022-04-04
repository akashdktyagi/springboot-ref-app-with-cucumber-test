package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.other.Utils;
import com.yantracloud.yantracloudrefappwithcucumbertest.repository.ProductRepository;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.net.URI;
import java.util.Map;
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
    private Scenario scenario;


    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @Value("${cucumbertest.auth.client-id}")
    private String clientID;
    @Value("${cucumbertest.auth.client-secret}")
    private String clientSecret;
    @Value("${cucumbertest.auth.audience}")
    private String audience;
    @Value("${cucumbertest.auth.token-url}")
    private String tokenUrl;

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
    public void client_calls_end_point_with_method_as_post(String endPoint) throws Exception {
        String access_token = Utils.getToken(tokenUrl,clientID,clientSecret,audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + access_token);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                post(URI.create(server + endPoint))
                .content(body)
                .headers(httpHeaders)
                .contentType("application/json");
        resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        scenario.log("Status Code: " + String.valueOf(resultActions.andReturn().getResponse().getStatus()));
        scenario.log("Response Message: " + String.valueOf(resultActions.andReturn().getResponse().getContentAsString()));
        scenario.log("Error Message: " + String.valueOf(resultActions.andReturn().getResponse().getErrorMessage()));
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

    @When("Client invokes the application to create new entity")
    public void client_invokes_the_application_to_create_new_product_entity() throws Exception {
        String access_token = Utils.getToken(tokenUrl,clientID,clientSecret,audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + access_token);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                post(URI.create(server + "/entity"))
                        .content(body)
                        .headers(httpHeaders)
                        .contentType("application/json");
        resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        scenario.log("Status Code: " + String.valueOf(resultActions.andReturn().getResponse().getStatus()));
        scenario.log("Response Message: " + String.valueOf(resultActions.andReturn().getResponse().getContentAsString()));
        scenario.log("Error Message: " + String.valueOf(resultActions.andReturn().getResponse().getErrorMessage()));
    }

    @Then("a new entity is created in the system")
    public void a_new_product_entity_is_created_in_the_system() throws Exception {
        resultActions.andExpect(status().is(200));
        String nameFromDB = productRepository.findByName(productDto.getName()).getName();
        Assertions.assertThat(productDto.getName()).isEqualTo(nameFromDB);
    }

    @Given("an entity already present with data as")
    public void an_entity_with_name_as_already_present(Map<String,String> map) throws Exception {
        productDto = ProductDto.builder()
                .withName(map.get("name"))
                .withCompany(map.get("company"))
                .withDescription(map.get("description"))
                .build();
        body = objectMapper.writeValueAsString(productDto);

        client_calls_end_point_with_method_as_post("/entity");
        client_receive_status_code_as(200);
    }

}
