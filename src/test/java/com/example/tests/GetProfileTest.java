package com.example.tests;

import com.example.base.AuthService;
import com.example.base.UserService;
import com.example.model.response.LoginResponse;
import com.example.model.response.UserProfileResponse;
import com.example.model.request.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.UserCredentials.password;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.*;

public class GetProfileTest {
    @Test(description = "Test get profile API")
    public void getProfileTest() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest(username, password));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken(), "Login token should not be null");
        assertFalse(loginResponse.getToken().isBlank(), "Login token should not be blank");
        UserService userService = new UserService();
        Response userServiceResponse = userService.getProfile(loginResponse.getToken());
        UserProfileResponse profileResponse = userServiceResponse.as(UserProfileResponse.class);
        assertEquals(profileResponse.getUsername(), username);
    }
}
