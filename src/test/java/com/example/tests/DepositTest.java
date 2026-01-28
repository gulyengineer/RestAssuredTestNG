package com.example.tests;

import com.example.base.TransactionService;
import com.example.model.request.DepositRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.example.utils.AuthTestUtils.loginAndGetToken;
import static com.example.utils.TestDataUtils.ACCOUNT_NUMBER;

@Listeners(com.example.listeners.TestListener.class)
public class DepositTest {
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(10.0);
    private static final String DESCRIPTION = "test";

    @Test(description = "Test deposit API")
    public void depositTest() {
        String token = loginAndGetToken();
        TransactionService transactionService = new TransactionService();
        DepositRequest payload = new DepositRequest(ACCOUNT_NUMBER, AMOUNT, DESCRIPTION);
        Response transactionServiceResponse = transactionService.deposit(token, payload);
        transactionServiceResponse.then().statusCode(200);
    }
}
