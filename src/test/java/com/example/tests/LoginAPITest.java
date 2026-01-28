package com.example.tests;

import com.example.base.AuthService;
import com.example.data.LoginDataProvider;
import com.example.model.LoginTestData;
import com.example.model.response.LoginResponse;
import com.example.model.request.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.utils.UserCredentials.email;
import static com.example.utils.UserCredentials.password;
import static com.example.utils.UserCredentials.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners(com.example.listeners.TestListener.class)
public class LoginAPITest {
    @Test(description = "Test login API happy path")
    public void loginWithValidCredentialsTest() {
        Response response = login(new LoginRequest(username, password));
        assertEquals(response.getStatusCode(), 200);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken(), "Login token should not be null");
        assertEquals(loginResponse.getEmail(), email);
    }

    //Invalid credentials scenario
    @Test(dataProvider = "negativeLoginData",
            dataProviderClass = LoginDataProvider.class)
    public void loginWithInvalidCredentialsTest(LoginTestData data) {
        Response response = login(new LoginRequest(data.username, data.password));
        assertEquals(response.getStatusCode(), data.expectedStatus, data.testCase);
    }

    private Response login(LoginRequest loginRequest) {
        AuthService authService = new AuthService();
        return authService.login(loginRequest);
    }
}
