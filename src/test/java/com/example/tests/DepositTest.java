package com.example.tests;

import com.example.base.TransactionService;
import com.example.model.request.DepositRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.AuthTestUtils.loginAndGetToken;
import static com.example.utils.TestDataUtils.ACCOUNT_NUMBER;

@Listeners(com.example.listeners.TestListener.class)
public class DepositTest {
    private static final double AMOUNT = 10.0;
    private static final String DESCRIPTION = "test";

    @Test(description = "Test deposit API")
    public void depositTest() {
        String token = loginAndGetToken();
        TransactionService transactionService = new TransactionService();
        DepositRequest request = new DepositRequest(ACCOUNT_NUMBER, AMOUNT, DESCRIPTION);
        Response transactionServiceResponse = transactionService.deposit(token, request);
        transactionServiceResponse.then().statusCode(200);
    }
}
