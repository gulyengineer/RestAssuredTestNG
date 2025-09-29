package com.example.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService { // Wrapper for Rest Assured
    public static final String BASE_URI = "http://64.227.160.186:8080/";
    private final RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification = RestAssured.given().baseUri(BASE_URI);
    }

    protected Response postRequest(Object payload, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }
}
