package com.example.tests;

import com.example.base.AuthService;
import com.example.base.UserService;
import com.example.model.request.LoginRequest;
import com.example.model.request.ProfileRequest;
import com.example.model.response.LoginResponse;
import com.example.model.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.TestDataUtils.DEFAULT_FIRST_NAME;
import static com.example.utils.TestDataUtils.DEFAULT_LAST_NAME;
import static com.example.utils.TestDataUtils.randomPhone;
import static com.example.utils.TestDataUtils.randomSuffix;
import static com.example.utils.UserCredentials.email;
import static com.example.utils.UserCredentials.password;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class UpdateProfileTest {
    @Test(description = "Test update profile API")
    public void updateProfileTest() {
        String firstName = DEFAULT_FIRST_NAME + " " + randomSuffix();
        String lastName = DEFAULT_LAST_NAME + " " + randomSuffix();
        String phone = randomPhone();

        String token = loginAndGetToken();
        UserService userService = new UserService();

        UserProfileResponse originalProfile = userService.getProfile(token).as(UserProfileResponse.class);
        ProfileRequest profileRequest = new ProfileRequest(firstName, lastName, email, phone);
        Response profileResponse = userService.updateProfile(token, profileRequest);

        UserProfileResponse updateProfileResponse = profileResponse.as(UserProfileResponse.class);
        assertEquals(updateProfileResponse.getFirstName(), firstName);

        ProfileRequest restoreRequest = new ProfileRequest(
                originalProfile.getFirstName(),
                originalProfile.getLastName(),
                originalProfile.getEmail(),
                originalProfile.getMobileNumber()
        );

        userService.updateProfile(token, restoreRequest);
    }

    private String loginAndGetToken() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(username, password));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        assertNotNull(token, "Login token should not be null");
        assertFalse(token.isBlank(), "Login token should not be blank");
        return token;
    }
}
