package com.example.base;

import io.restassured.response.Response;

public class UserService extends BaseService {
    private static final String BASE_PATH = "/api/users/";

    public Response getProfile(String authToken) {
        setAuthToken(authToken);
        return getRequest(BASE_PATH + "profile");
    }

}
