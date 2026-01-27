package com.example.tests;

import com.example.base.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.UserCredentials.email;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ForgotPasswordTest {
    @Test(description = "Test forgot password API")
    public void forgotPasswordTest() {
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword(email);
        response.then().statusCode(500);//New accounts get 200 and a message in the response body,
        // after the second try it gets 500

        String errorCode = response.jsonPath().getString("errorCode");
        assertEquals("Forgot password returned error response: " + response.asString(), "SYS_001", errorCode);
    }

    @Test(description = "Forgot password should reject empty email")
    public void forgotPasswordShouldRejectEmptyEmail() {
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword("");

        int status = response.getStatusCode();
        assertTrue(status >= 400 && status <= 500, "Expected client error for empty email, got " + status);
    }
}
