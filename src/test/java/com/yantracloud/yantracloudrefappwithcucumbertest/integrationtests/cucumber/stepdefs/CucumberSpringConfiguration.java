package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 Some Details on Annotations.
@SpringBootTest -  to be used for bring the whole Spring Boot Application Context
@WebMvcTest  -- to be used if You only want to bring the Mvc related app and now whole Spring Application Context
@ContextConfiguration -- to be used if you are simply using Spring MVC features and not the Spring Boot
 */
@SpringBootTest
@CucumberContextConfiguration
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class CucumberSpringConfiguration {
}
