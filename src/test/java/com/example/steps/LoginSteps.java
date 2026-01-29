package com.example.steps;

import com.example.base.AuthService;
import com.example.model.request.LoginRequest;
import com.example.model.response.LoginResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.utils.EnvVariables.email;
import static com.example.utils.EnvVariables.password;
import static com.example.utils.EnvVariables.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginSteps {
    private LoginRequest loginRequest;
    private Response response;
    private LoginResponse loginResponse;

    @Given("valid login credentials")
    public void valid_login_credentials() {
        loginRequest = new LoginRequest(username, password);
    }

    @When("I call the login API")
    public void i_call_the_login_api() {
        response = new AuthService().login(loginRequest);
    }

    @Then("the response status is {int}")
    public void the_response_status_is(int status) {
        assertEquals(response.getStatusCode(), status);
    }

    @Then("the login token is present")
    public void the_login_token_is_present() {
        assertNotNull(getLoginResponse().getToken(), "Login token should not be null");
    }

    @Then("the response email matches the test email")
    public void the_response_email_matches_the_test_email() {
        assertEquals(getLoginResponse().getEmail(), email);
    }

    private LoginResponse getLoginResponse() {
        if (loginResponse == null) {
            loginResponse = response.as(LoginResponse.class);
        }
        return loginResponse;
    }
}
