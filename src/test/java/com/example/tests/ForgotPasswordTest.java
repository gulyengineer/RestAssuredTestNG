package com.example.tests;

import com.example.base.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.UserCredentials.email;

public class ForgotPasswordTest {
    @Test(description = "Test forgot password API")
    public void forgotPasswordTest() {
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword(email);
        System.out.println(response.asPrettyString());
    }
}
