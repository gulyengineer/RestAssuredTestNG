package com.example.utils;

public class UserCredentials {
    public static final String username;
    public static final String password;
    public static final String email;

    static {
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        email = System.getenv("EMAIL");
        // Allow partial presence to support tests requiring only a subset (e.g., email only).
        // However, fail fast if none of the expected credentials are provided.
        if (username == null && password == null && email == null) {
            throw new IllegalStateException(
                    "No credential environment variables found. At least one of USERNAME, PASSWORD, or EMAIL must be set."
            );
        }
    }
}