package com.example.tests;

import com.example.base.AuthService;
import com.example.base.UserService;
import com.example.model.response.LoginResponse;
import com.example.model.response.UserProfileResponse;
import com.example.models.request.LoginRequest;
import com.example.models.request.ProfileRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

import static com.example.utils.UserCredentials.*;
import static org.testng.Assert.*;

public class UpdateProfileTest {
    @Test(description = "Test update profile API")
    public void updateProfileTest() {
        Random random = new Random();
        String firstName = "User " + UUID.randomUUID().toString().charAt(0);
        String lastName = "Test " + UUID.randomUUID().toString().charAt(0);
        String phone = String.format("%010d", random.nextInt(1_000_000_000));

        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(username, password));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken(), "Login token should not be null");
        assertFalse(loginResponse.getToken().isBlank(), "Login token should not be blank");
        UserService userService = new UserService();
        ProfileRequest profileRequest = new ProfileRequest(firstName, lastName, email, phone);
        Response profileResponse = userService.updateProfile(loginResponse.getToken(), profileRequest);

        UserProfileResponse updateProfileResponse = profileResponse.as(UserProfileResponse.class);
        assertEquals(updateProfileResponse.getFirstName(), firstName);

    }
}
