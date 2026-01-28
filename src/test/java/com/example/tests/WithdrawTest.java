package com.example.tests;

import com.example.base.TransactionService;
import com.example.model.request.WithdrawRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.AuthTestUtils.loginAndGetToken;
import static com.example.utils.TestDataUtils.ACCOUNT_NUMBER;

@Listeners(com.example.listeners.TestListener.class)
public class WithdrawTest {
    private static final double AMOUNT = 10.0;
    private static final String DESCRIPTION = "test";


    @Test(description = "Test withdraw API")
    public void testWithdraw() {

        String token = loginAndGetToken();
        TransactionService transactionService = new TransactionService();
        WithdrawRequest payload = new WithdrawRequest(ACCOUNT_NUMBER, AMOUNT, DESCRIPTION);
        Response accountServiceResponse = transactionService.withdraw(token, payload);
        accountServiceResponse.then().statusCode(200);
    }

}
