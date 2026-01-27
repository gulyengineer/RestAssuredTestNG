package com.example.tests;

import com.example.base.AuthService;
import com.example.model.request.SignupRequest;
import com.example.utils.UserCredentials;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.utils.TestDataUtils.*;
import static org.testng.Assert.assertEquals;

public class SignupTest {
    @Test(description = "Test signup API happy path")
    public void signupTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        Response response = signup(signupRequest);
        assertEquals(response.asPrettyString(), "User registered successfully!");
    }

    // Username is already taken scenario
    @Test(description = "Test signup API with existing username")
    public void signupWithExistingUsernameTest() {
        SignupRequest signupRequest = buildSignupRequest(EXISTING_USERNAME, randomEmail());
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
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), INVALID_EMAIL);
        Response response = signup(signupRequest);
        assertEquals(response.getStatusCode(), 500);
    }

    //  Invalid password scenario
    @Test(description = "Test signup API with invalid password")
    public void signupWithInvalidPasswordTest() {
        SignupRequest signupRequest = buildSignupRequest(randomUsername(), randomEmail());
        signupRequest.setPassword(INVALID_PASSWORD);
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
        signupRequest.setMobileNumber(INVALID_PHONE);
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
        return new SignupRequest(username, DEFAULT_PASSWORD, email, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, randomPhone());
    }
}
