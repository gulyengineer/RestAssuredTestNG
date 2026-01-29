package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example.steps",
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
