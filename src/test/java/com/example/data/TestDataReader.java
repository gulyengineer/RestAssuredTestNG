package com.example.data;

import com.example.model.LoginTestData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TestDataReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String LOGIN_NEGATIVE_DATA = "login-negative-test-data.json";

    public static List<LoginTestData> getLoginNegativeData() {
        try (var in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(LOGIN_NEGATIVE_DATA)) {
            if (in == null) {
                throw new IllegalStateException("Missing test resource: " + LOGIN_NEGATIVE_DATA);
            }
            return MAPPER.readValue(in, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read " + LOGIN_NEGATIVE_DATA, e);
        }
    }

}
