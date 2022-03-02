package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantracloud.yantracloudrefappwithcucumbertest.dto.ProductDto;
import com.yantracloud.yantracloudrefappwithcucumbertest.repository.ProductRepository;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommonStepDefs {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.host}")
    private String server;

    ResultActions _resultActions;
    String _body;
    String _url;
    HttpHeaders _httpHeaders = new HttpHeaders();
    private Scenario _scenario;


//    MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
//            post(URI.create(server + endPoint))
//                    .content(body)
//                    .contentType("application/json");
//
//    resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

    @Before
    public void setUp(Scenario scenario){
        _scenario = scenario;
    }

    @Given("with end point url as {string}")
    public void with_end_point_url_as(String endPoint) {
        _url = server + endPoint;
    }
    @Given("with body as {string}")
    public void with_body_as(String string) {
        _body = string;
    }
    @Given("with headers as in below table")
    public void with_headers_in_json_format_as(Map<String,String> headers) {
        headers.forEach(_httpHeaders::add);
    }
    @Given("with mock as {string}")
    public void with_mock_as_(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("with method as {string}")
    public void with_method_as(String methodName) throws Exception {
        switch (methodName){
            case "get":
                _resultActions = mockMvc.perform(get(URI.create(_url))
                        .headers(_httpHeaders));
                break;
            case "post":
                _resultActions = mockMvc.perform(post(URI.create(_url))
                        .content(_body)
                        .headers(_httpHeaders));
                break;
        }
        _scenario.log("Response as String: " + String.valueOf(_resultActions.andReturn().getResponse().getContentAsString()));

    }
    @Then("status is {string}")
    public void status_is(String code) throws Exception {
        _scenario.log("Status: " + String.valueOf(_resultActions.andReturn().getResponse().getStatus()));
        _resultActions.andExpect(status().is(Integer.parseInt(code)));
    }
    @Then("response contains string as {string}")
    public void response_contains_string_as(String expected) throws Exception {
        _resultActions.andExpect(content().string(containsString(expected)));

//        _resultActions.andExpect(jsonPath("$.yourKeyValue", is("WhatYouExpect")))
    }
    @Then("response json path as {string} has value which contains {string}")
    public void response_json_path_as_has_value_which_contains(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("entity of type {string} is created")
    public void entity_of_type_is_created(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("with swagger schema document location as {string}")
    public void with_swagger_schema_document_location_as(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("with query parameter as in below table")
    public void with_query_parameter_as_in_below_table() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("with authorization mechanism as {string}")
    public void with_authorization_mechanism_as(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response does not contains string as {string}")
    public void response_does_not_contains_string_as(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response contains string mentioned in below list")
    public void response_contains_string_mentioned_in_below_list() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response does not contains string mentioned in below list")
    public void response_does_not_contains_string_mentioned_in_below_list() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("response json path as {string} has value which does not contains {string}")
    public void response_json_path_as_has_value_which_does_not_contains(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response json path as {string} has value equal to {string}")
    public void response_json_path_as_has_value_equal_to(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response json path and values mentioned in below table")
    public void response_json_path_and_values_mentioned_in_below_table(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("response json path {string} is not null")
    public void response_json_path_is_not_null(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("response json path is not null in below list")
    public void response_json_path_is_not_null_in_below_list(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("response field represented as json path in below list is present in response")
    public void response_field_represented_as_json_path_in_below_list_is_present_in_response(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("response field represented as xml path in below list is present in response")
    public void response_field_represented_as_xml_path_in_below_list_is_present_in_response(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("response field represented as json path in below list is not present in response")
    public void response_field_represented_as_json_path_in_below_list_is_not_present_in_response(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @Then("response field represented as xml path in below list is not present in response")
    public void response_field_represented_as_xml_path_in_below_list_is_not_present_in_response(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }



}
