package com.example.base;

import com.example.models.request.ProfileRequest;
import io.restassured.response.Response;

public class UserService extends BaseService {
    private static final String BASE_PATH = "/api/users/";

    public Response getProfile(String authToken) {
        setAuthToken(authToken);
        return getRequest(BASE_PATH + "profile");
    }

    public Response updateProfile(String authToken, ProfileRequest payload) {
        setAuthToken(authToken);
        return putRequest(payload, BASE_PATH + "profile");
    }

}
