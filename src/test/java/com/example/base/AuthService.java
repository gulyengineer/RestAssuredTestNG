package com.example.base;

import com.example.models.request.LoginRequest;
import com.example.models.request.SignupRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public class AuthService extends BaseService {
    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest payload) {
        return postRequest(payload, BASE_PATH + "login");
    }

    public Response signup(SignupRequest payload) {
        return postRequest(payload, BASE_PATH + "signup");
    }

    public Response forgotPassword(String emailAddress) {
        HashMap<String, String> payload = new HashMap<>();
        payload.put("email", emailAddress);
        return postRequest(payload, BASE_PATH + "forgot-password");
    }
}
