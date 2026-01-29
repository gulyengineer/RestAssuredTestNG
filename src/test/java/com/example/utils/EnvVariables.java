package com.example.utils;

import io.github.cdimascio.dotenv.Dotenv;

import static org.testng.util.Strings.isNullOrEmpty;


public class EnvVariables {
    public static final String username;
    public static final String password;
    public static final String email;
    public static final String BASE_URI;

    static {
        // Load .env file (if present)
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing() // safe if .env doesn't exist
                .load();

        // Try to read from system environment first, then fallback to .env
        username = getEnvOrDotenv("TEST_USERNAME", dotenv);
        password = getEnvOrDotenv("TEST_PASSWORD", dotenv);
        email = getEnvOrDotenv("TEST_EMAIL", dotenv);
        BASE_URI = getEnvOrDotenv("BASE_URI", dotenv);

        if (isNullOrEmpty(username) || isNullOrEmpty(password) || isNullOrEmpty(email) || isNullOrEmpty(BASE_URI)) {
            throw new IllegalStateException(
                    "Required environment variable missing/blank. Please set TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, and BASE_URI in your environment or .env file."
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
