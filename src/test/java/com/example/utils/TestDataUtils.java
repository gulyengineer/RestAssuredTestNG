package com.example.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class TestDataUtils {
    public static final String DEFAULT_PASSWORD = "Password123!";
    public static final String DEFAULT_FIRST_NAME = "User";
    public static final String DEFAULT_LAST_NAME = "Test";
    public static final String EXISTING_USERNAME = "TestUser";
    public static final String INVALID_USERNAME = "invalid_username";
    public static final String INVALID_PASSWORD = "123";
    public static final String INVALID_EMAIL = "invalid_email@";
    public static final String INVALID_PHONE = "123";
    private static final String USERNAME_PREFIX = "TestUser";
    private static final String EMAIL_PREFIX = "user_";
    private static final String EMAIL_DOMAIN = "@test.com";

    private TestDataUtils() {
    }

    public static String randomUsername() {
        return USERNAME_PREFIX + System.currentTimeMillis();
    }

    public static String randomEmail() {
        return EMAIL_PREFIX + UUID.randomUUID().toString().substring(0, 8) + EMAIL_DOMAIN;
    }

    public static String randomSuffix() {
        return String.valueOf(UUID.randomUUID().toString().charAt(0));
    }

    // Generates a 10-digit number starting with a digit from 1-9
    public static String randomPhone() {
        int firstDigit = ThreadLocalRandom.current().nextInt(1, 10);
        int remainingDigits = ThreadLocalRandom.current().nextInt(1_000_000_000);
        return firstDigit + String.format("%09d", remainingDigits);
    }
}
