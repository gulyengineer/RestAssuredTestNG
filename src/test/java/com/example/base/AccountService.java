package com.example.base;

import com.example.model.request.CreateAccountRequest;
import io.restassured.response.Response;

public class AccountService extends BaseService {
    private static final String BASE_PATH = "/api/accounts/";

    public Response createAccount(String authToken, CreateAccountRequest payload) {
        setAuthToken(authToken);
        return postRequest(payload, BASE_PATH);
    }

}
