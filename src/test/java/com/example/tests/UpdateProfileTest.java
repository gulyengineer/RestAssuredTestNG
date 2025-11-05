package com.example.tests;

import com.example.base.AuthService;
import com.example.base.UserService;
import com.example.model.response.LoginResponse;
import com.example.model.response.UserProfileResponse;
import com.example.model.request.LoginRequest;
import com.example.model.request.ProfileRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

import static com.example.utils.UserCredentials.*;
import static org.testng.Assert.*;

public class UpdateProfileTest {
    private UserProfileResponse originalProfile;
    private String token;

    @Test(description = "Test update profile API")
    public void updateProfileTest() {
        Random random = new Random();
        String firstName = "User " + UUID.randomUUID().toString().charAt(0);
        String lastName = "Test " + UUID.randomUUID().toString().charAt(0);
        String phone = String.format("%010d", random.nextInt(1_000_000_000));

        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(username, password));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        token = loginResponse.getToken();
        assertNotNull(token, "Login token should not be null");
        assertFalse(token.isBlank(), "Login token should not be blank");
        UserService userService = new UserService();
        ProfileRequest profileRequest = new ProfileRequest(firstName, lastName, email, phone);
        // Get and store the original profile data
        originalProfile = userService.getProfile(token).as(UserProfileResponse.class);
        Response profileResponse = userService.updateProfile(token, profileRequest);

        UserProfileResponse updateProfileResponse = profileResponse.as(UserProfileResponse.class);
        assertEquals(updateProfileResponse.getFirstName(), firstName);

        // Restore the original profile data
        ProfileRequest restoreRequest = new ProfileRequest(originalProfile.getFirstName(), originalProfile.getLastName(), originalProfile.getEmail(),originalProfile.getMobileNumber());
        userService.updateProfile(this.token, restoreRequest);
    }
}
