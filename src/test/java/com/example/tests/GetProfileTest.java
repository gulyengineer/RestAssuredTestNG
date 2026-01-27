package com.example.tests;

import com.example.base.UserService;
import com.example.model.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.AuthTestUtils.loginAndGetToken;
import static com.example.utils.UserCredentials.email;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(com.example.listeners.TestListener.class)
public class GetProfileTest {
    @Test(description = "Test get profile API")
    public void getProfileTest() {
        String token = loginAndGetToken();

        UserService userService = new UserService();
        Response userServiceResponse = userService.getProfile(token);
        userServiceResponse.then().statusCode(200);

        UserProfileResponse profileResponse = userServiceResponse.as(UserProfileResponse.class);
        assertEquals(profileResponse.getUsername(), username, "Username should match login user");
        assertEquals(profileResponse.getEmail(), email, "Email should match login user");
        assertTrue(profileResponse.getId() > 0, "User id should be positive");
    }
}
