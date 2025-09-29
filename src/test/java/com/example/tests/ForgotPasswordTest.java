package com.example.tests;

import com.example.base.AuthService;
import com.example.utils.CommonDataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordTest {
    @Test(description = "Test forgot password API", dataProvider = "emailFromEnv", dataProviderClass = CommonDataProviders.class)
    public void forgotPasswordTest(String email) {
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword(email);
        System.out.println(response.asPrettyString());
    }
}
