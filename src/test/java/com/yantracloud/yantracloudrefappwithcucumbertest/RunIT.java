package com.yantracloud.yantracloudrefappwithcucumbertest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "classpath:features",
        glue = "com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs",
        plugin = {"pretty","summary",
                "html:target/cucumber-reports.html",
                "json:target/json_result.json"
        },
        tags = "not @manual",
        dryRun = false
)
@RunWith(Cucumber.class)
public class RunIT {




}
