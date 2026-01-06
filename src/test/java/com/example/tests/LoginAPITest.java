package com.example.tests;

import com.example.base.AuthService;
import com.example.model.response.LoginResponse;
import com.example.model.request.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.UserCredentials.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginAPITest {
    @Test(description = "Test login API")
    public void login() {
        AuthService authService = new AuthService();
        LoginRequest loginRequest = new LoginRequest(username, password);
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken(), "Login token should not be null");
        assertEquals(loginResponse.getEmail(), email);
        assertEquals(loginResponse.getId(), 2891);
    }
}