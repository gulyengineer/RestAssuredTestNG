package com.example.utils;

import com.example.base.AuthService;
import com.example.model.request.LoginRequest;
import com.example.model.response.LoginResponse;
import io.restassured.response.Response;

import static com.example.utils.UserCredentials.password;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public final class AuthTestUtils {
    private AuthTestUtils() {
    }

    public static String loginAndGetToken() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(username, password));
        assertEquals(response.getStatusCode(), 200, "Expected login to succeed with status 200");

        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        assertNotNull(token, "Login token should not be null");
        assertFalse(token.isBlank(), "Login token should not be blank");
        return token;
    }
}
