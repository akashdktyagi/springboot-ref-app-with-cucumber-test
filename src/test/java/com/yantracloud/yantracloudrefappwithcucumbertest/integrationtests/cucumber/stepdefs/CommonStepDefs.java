package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.other.TestContext;
import com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.other.Utils;
import com.yantracloud.yantracloudrefappwithcucumbertest.pojo.Auth0ClientCredentialFlowRequest;
import com.yantracloud.yantracloudrefappwithcucumbertest.pojo.Auth0ClientCredentialFlowResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Preconditions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

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

    @Value("${cucumbertest.auth.client-id}")
    private String clientID;
    @Value("${cucumbertest.auth.client-secret}")
    private String clientSecret;
    @Value("${cucumbertest.auth.audience}")
    private String audience;
    @Value("${cucumbertest.auth.token-url}")
    private String tokenUrl;

    ResultActions _resultActions;
    String _body;
    String _url;
    String _access_token;
    HttpHeaders _httpHeaders = new HttpHeaders();

    @Autowired
    TestContext testContext;

    private Scenario _scenario;

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

    @Given("with body json as")
    public void with_body_as_doc_string(String doc_string) {
        _body = doc_string;
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
                        .headers(_httpHeaders))
                ;
                break;
            case "post":
                _resultActions = mockMvc.perform(post(URI.create(_url))
                        .content(_body)
                        .contentType("application/json")
                        .headers(_httpHeaders));
                break;
            case "put":
                _resultActions = mockMvc.perform(put(URI.create(_url))
                        .content(_body)
                        .contentType("application/json")
                        .headers(_httpHeaders));
            case "delete":
                _resultActions = mockMvc.perform(delete(URI.create(_url))
                        .headers(_httpHeaders));
                break;
            default:
                _scenario.log("This method is not supported." + methodName);
        }
        _scenario.log("Status: " + String.valueOf(_resultActions.andReturn().getResponse().getStatus()));
        _scenario.log("Error Message: " + String.valueOf(_resultActions.andReturn().getResponse().getErrorMessage()));
        _scenario.log("Response as String: " + String.valueOf(_resultActions.andReturn().getResponse().getContentAsString()));

    }

    @Given("with acquired token and with authorization flow as {string}")
    public void with_acquired_token_and_with_auth_flow_as(String authType) throws Exception {
        _access_token = Utils.getToken(tokenUrl,clientID,clientSecret,audience);
        _httpHeaders.add("Authorization","Bearer " + _access_token);
        _scenario.log("Access token received: " + _access_token);
    }

    @Then("status is {string}")
    public void status_is(String code) throws Exception {
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
