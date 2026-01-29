package com.example.base;

import com.example.model.request.DepositRequest;
import com.example.model.request.WithdrawRequest;
import io.restassured.response.Response;

public class TransactionService extends BaseService {

    private static final String BASE_PATH = "/api/transactions/";
    private static final String DEPOSIT_PATH = BASE_PATH + "deposit";
    private static final String WITHDRAW_PATH = BASE_PATH + "withdraw";

    public Response deposit(String authToken, DepositRequest payload) {
        setAuthToken(authToken);
        return postRequest(payload, DEPOSIT_PATH);
    }

    public Response withdraw(String authToken, WithdrawRequest payload) {
        setAuthToken(authToken);
        return postRequest(payload, WITHDRAW_PATH);
    }

}
