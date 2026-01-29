package com.example.tests;

import com.example.base.AccountService;
import com.example.model.request.CreateAccountRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.AuthTestUtils.loginAndGetToken;

@Listeners(com.example.listeners.TestListener.class)
public class CreateAccountTest {
    private static final String ACCOUNT_TYPE = "SAVINGS";
    private static final String BRANCH = "NORTH_BRANCH";

    @Test(description = "Test create account API")
    public void createAccountTest() {
        String token = loginAndGetToken();
        AccountService accountService = new AccountService();
        CreateAccountRequest payload = new CreateAccountRequest(ACCOUNT_TYPE, BRANCH);
        Response accountServiceResponse = accountService.createAccount(token, payload);
        accountServiceResponse.then().statusCode(200);
    }
}
