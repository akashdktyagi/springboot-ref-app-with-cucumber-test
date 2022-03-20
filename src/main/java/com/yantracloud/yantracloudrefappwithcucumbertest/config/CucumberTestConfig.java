package com.yantracloud.yantracloudrefappwithcucumbertest.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("cucumbertest")
@Profile("test")
@Data
public class CucumberTestConfig {
    String clientID;
    String clientSecret;
    String audience;
    String tokenUrl;
}
