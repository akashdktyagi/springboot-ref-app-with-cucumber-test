package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "classpath:features",
        glue = "com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.cucumber.stepdefs",
        plugin = {"pretty","summary",
                "html:target/cucumber-reports.html",
                "json:target/json_result.json"
        },
        tags = "not @manual and @post",
        dryRun = false
)
@RunWith(Cucumber.class)
public class RunIT {
}
