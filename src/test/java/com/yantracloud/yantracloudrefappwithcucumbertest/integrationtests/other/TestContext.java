package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.other;

import io.cucumber.spring.ScenarioScope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ScenarioScope
public class TestContext {
    ResultActions resultActions;
    String body;
    String url;
    String access_token;
    HttpHeaders httpHeaders = new HttpHeaders();
}
