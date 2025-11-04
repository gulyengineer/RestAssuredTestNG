package com.example.tests;

import com.example.base.AuthService;
import com.example.models.request.SignupRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class SignupTest {
    @Test(description = "Test signup API")
    public void signupTest() {
        Random random = new Random();
        String username = "TestUser" + System.currentTimeMillis();
        String password = "Password123!";
        String email = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        String firstName = "User";
        String lastName = "Test";
        // Generates a 10-digit number starting with a digit from 1-9
        String phone = (random.nextInt(9) + 1) + String.format("%09d", random.nextInt(1_000_000_000));

        AuthService authService = new AuthService();
        SignupRequest signupRequest = new SignupRequest(username, password, email, firstName, lastName, phone);
        Response response = authService.signup(signupRequest);
        assertEquals(response.asPrettyString(), "User registered successfully!");
    }
}
