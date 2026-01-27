package com.example.tests;

import com.example.base.AuthService;
import com.example.model.request.SignupRequest;
import com.example.utils.UserCredentials;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertEquals;

public class SignupTest {
    private static final String PASSWORD = "Password123!";
    private static final String FIRST_NAME = "User";
    private static final String LAST_NAME = "Test";
    private static final String EMAIL_DOMAIN = "@test.com";

    @Test(description = "Test signup API happy path")
    public void signupTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        Response response = signup(signupRequest);
        assertEquals(response.asPrettyString(), "User registered successfully!");
    }

    // Username is already taken scenario
    @Test(description = "Test signup API with existing username")
    public void signupWithExistingUsernameTest() {
        SignupRequest signupRequest = buildSignupRequest("TestUser", randomEmail());
        Response response = signup(signupRequest);
        assertEquals(response.asPrettyString(), "Error: Username is already taken!");
        assertEquals(response.getStatusCode(), 400);
    }

    // Existing email scenario
    @Test(description = "Test signup API with existing email")
    public void signupWithExistingEmailTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), UserCredentials.email);
        Response response = signup(signupRequest);
        assertEquals(response.asPrettyString(), "Error: Email is already in use!");
        assertEquals(response.getStatusCode(), 400);

    }

    // Invalid email scenario
    @Test(description = "Test signup API with invalid email")
    public void signupWithInvalidEmailTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), "invalid_email@");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //  Invalid password scenario
    @Test(description = "Test signup API with invalid password")
    public void signupWithInvalidPasswordTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setPassword("123");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }


    //Missing username scenario
    @Test(description = "Test signup API with missing username")
    public void signupWithMissingUsernameTest() {
        SignupRequest signupRequest = buildSignupRequest("", randomEmail());
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Missing email scenario
    @Test(description = "Test signup API with missing email")
    public void signupWithMissingEmailTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), "");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Missing password scenario
    @Test(description = "Test signup API with missing password")
    public void signupWithMissingPasswordTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setPassword("");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Invalid phone number scenario
    @Test(description = "Test signup API with invalid phone number")
    public void signupWithInvalidPhoneTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setMobileNumber("123");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Missing phone number scenario
    @Test(description = "Test signup API with missing phone number")
    public void signupWithMissingPhoneTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setMobileNumber("");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Missing first name scenario
    @Test(description = "Test signup API with missing first name")
    public void signupWithMissingFirstNameTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setFirstName("");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //Missing last name scenario
    @Test(description = "Test signup API with missing last name")
    public void signupWithMissingLastNameTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setLastName("");
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }


    private Response signup(SignupRequest signupRequest) {
        AuthService authService = new AuthService();
        return authService.signup(signupRequest);
    }

    private SignupRequest buildSignupRequest(String username, String email) {
        return new SignupRequest(username, PASSWORD, email, FIRST_NAME, LAST_NAME, randomPhone());
    }

    private String randomUsername() {
        return "TestUser" + System.currentTimeMillis();
    }

    private String randomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + EMAIL_DOMAIN;
    }

    // Generates a 10-digit number starting with a digit from 1-9
    private String randomPhone() {
        int firstDigit = ThreadLocalRandom.current().nextInt(1, 10);
        int remainingDigits = ThreadLocalRandom.current().nextInt(1_000_000_000);
        return firstDigit + String.format("%09d", remainingDigits);
    }
}
