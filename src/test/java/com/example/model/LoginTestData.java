package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginTestData {
    public String testCase;
    public String username;
    public String password;
    public int expectedStatus;
}
