package com.example.tests;

import com.example.base.AuthService;
import com.example.model.response.LoginResponse;
import com.example.models.request.LoginRequest;
import com.example.utils.CommonDataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginAPITest {
    @Test(description = "Test login API", dataProvider = "credentialsFromEnv", dataProviderClass = CommonDataProviders.class)
    public void login(String username, String password, String email) {
        AuthService authService = new AuthService();
        LoginRequest loginRequest = new LoginRequest(username, password);
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        assertNotNull(loginResponse.getToken());
        assertEquals(loginResponse.getEmail(), email);
        assertEquals(loginResponse.getId(), 2891);
    }
}