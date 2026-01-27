package com.example.tests;

import com.example.base.AuthService;
import com.example.model.response.LoginResponse;
import com.example.model.request.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.TestDataUtils.INVALID_PASSWORD;
import static com.example.utils.TestDataUtils.INVALID_USERNAME;
import static com.example.utils.UserCredentials.email;
import static com.example.utils.UserCredentials.password;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners(com.example.listeners.TestListener.class)
public class LoginAPITest {
    @Test(description = "Test login API happy path")
    public void loginWithValidCredentialsTest() {
        Response response = login(new LoginRequest(username, password));
        assertEquals(response.getStatusCode(), 200);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken(), "Login token should not be null");
        assertEquals(loginResponse.getEmail(), email);
    }

    //Invalid credentials scenario
    @Test(description = "Test login API with invalid credentials")
    public void loginWithInvalidCredentialsTest() {
        Response response = login(new LoginRequest(INVALID_USERNAME, INVALID_PASSWORD));
        assertEquals(response.getStatusCode(), 401);
    }

    private Response login(LoginRequest loginRequest) {
        AuthService authService = new AuthService();
        return authService.login(loginRequest);
    }
}
