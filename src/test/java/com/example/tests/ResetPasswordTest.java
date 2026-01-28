package com.example.tests;

import static com.example.utils.AuthTestUtils.loginAndGetToken;

import com.example.base.UserService;
import com.example.model.request.ResetPasswordRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.TestDataUtils.DEFAULT_PASSWORD;
import static com.example.utils.UserCredentials.password;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTest {
    @Test(description = "Test reset password API")
    public void resetPasswordHappyPathTest() {
        String currentPassword = password;
        String newPassword = DEFAULT_PASSWORD;
        String token = loginAndGetToken();

        UserService userService = new UserService();
        Response changeResponse = userService.changePassword(
                token,
                new ResetPasswordRequest(currentPassword, newPassword, newPassword)
        );
        assertEquals(changeResponse.getStatusCode(), 200);

        Response restoreResponse = userService.changePassword(
                token,
                new ResetPasswordRequest(newPassword, currentPassword, currentPassword)
        );
        assertEquals(restoreResponse.getStatusCode(), 200);
    }

    @Test(description = "Change password should reject empty token")
    public void changePasswordShouldRejectEmptyToken() {
        UserService userService = new UserService();
        ResetPasswordRequest request = new ResetPasswordRequest(password, DEFAULT_PASSWORD, DEFAULT_PASSWORD);
        Response response = userService.changePassword("", request);

        int status = response.getStatusCode();
        assertTrue(status >= 400 && status <= 500, "Expected client error for empty token, got " + status);
    }

    @Test(description = "Change password should reject empty password")
    public void changePasswordShouldRejectEmptyPassword() {
        UserService userService = new UserService();
        String token = loginAndGetToken();

        ResetPasswordRequest request = new ResetPasswordRequest(password, "", "");
        Response response = userService.changePassword(token, request);
        int status = response.getStatusCode();
        assertTrue(status >= 400 && status <= 500, "Expected client error for empty token, got " + status);
    }
}
