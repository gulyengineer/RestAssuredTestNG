package com.example.base;

import com.example.models.request.LoginRequest;
import com.example.models.request.SignupRequest;
import io.restassured.response.Response;

public class AuthService extends BaseService {
    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest payload) {
        return postRequest(payload, BASE_PATH + "login");
    }

    public Response signup(SignupRequest payload) {
        return postRequest(payload, BASE_PATH + "signup");
    }
}
