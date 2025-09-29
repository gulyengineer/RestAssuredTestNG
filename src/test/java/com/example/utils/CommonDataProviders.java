package com.example.utils;

import org.testng.annotations.DataProvider;

public class CommonDataProviders {

    @DataProvider(name = "credentialsFromEnv")
    public static Object[][] credentialsFromEnv() {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String email = System.getenv("EMAIL");
        if (username == null || password == null || email == null) {
            throw new IllegalStateException("Missing environment variables USERNAME, PASSWORD, EMAIL");
        }
        return new Object[][]{{username, password, email}};
    }

    @DataProvider(name = "emailFromEnv")
    public static Object[][] emailFromEnv() {
        String email = System.getenv("EMAIL");
        return new Object[][]{{email}};
    }
}
