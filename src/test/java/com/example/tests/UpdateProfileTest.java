package com.example.tests;

import com.example.base.UserService;
import com.example.model.request.ProfileRequest;
import com.example.model.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.AuthTestUtils.loginAndGetToken;
import static com.example.utils.TestDataUtils.DEFAULT_FIRST_NAME;
import static com.example.utils.TestDataUtils.DEFAULT_LAST_NAME;
import static com.example.utils.TestDataUtils.randomPhone;
import static com.example.utils.TestDataUtils.randomSuffix;
import static com.example.utils.UserCredentials.email;
import static org.testng.Assert.assertEquals;

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

        Response restoreResponse = userService.updateProfile(token, restoreRequest);
        assertEquals(restoreResponse.getStatusCode(), 200, "Profile restoration failed");
    }
}
