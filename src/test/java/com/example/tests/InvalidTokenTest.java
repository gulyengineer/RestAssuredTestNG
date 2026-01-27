package com.example.tests;

import com.example.base.UserService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class InvalidTokenTest {

    @Test
    public void loginWithInvalidTokenShouldFail() {
        UserService userService = new UserService();
        Response response = userService.getProfile("invalid_token");

        response.then().statusCode(500);
    }

}
