package com.example.tests;

import com.example.base.AuthService;
import com.example.model.response.LoginResponse;
import com.example.models.request.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginAPITest {
    @DataProvider(name = "credentialsFromEnv")
    public Object[][] credentialsFromEnv() {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String expectedEmail = System.getenv("EXPECTED_EMAIL");

        // If you prefer system properties (mvn -D...), use System.getProperty("USERNAME")
        if (username == null || password == null || expectedEmail == null) {
            throw new IllegalStateException("Missing environment variables USERNAME, PASSWORD or EXPECTED_EMAIL");
        }

        return new Object[][]{{username, password, expectedEmail}};
    }

    @Test(description = "Test login API", dataProvider = "credentialsFromEnv")
    public void login(String username, String password, String expectedEmail) {
        AuthService authService = new AuthService();
        LoginRequest loginRequest = new LoginRequest(username, password);
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        assertNotNull(loginResponse.getToken());
        assertEquals(loginResponse.getEmail(), expectedEmail);
        assertEquals(loginResponse.getId(), 2891);
    }
}