package com.example.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class UserCredentials {
    public static final String username;
    public static final String password;
    public static final String email;

    static {
        // Load .env file (if present)
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing() // safe if .env doesn't exist
                .load();

        // Try to read from system environment first, then fallback to .env
        username = getEnvOrDotenv("TEST_USERNAME", dotenv);
        password = getEnvOrDotenv("TEST_PASSWORD", dotenv);
        email = getEnvOrDotenv("TEST_EMAIL", dotenv);

        if (username == null || password == null || email == null) {
            throw new IllegalStateException(
                    "Credential environment variable not found. Please set USERNAME, PASSWORD, or EMAIL in your environment or .env file."
            );
        }
    }

    private static String getEnvOrDotenv(String key, Dotenv dotenv) {
        String value = System.getenv(key);
        if (value == null) {
            value = dotenv.get(key);
        }
        return value;
    }
}
