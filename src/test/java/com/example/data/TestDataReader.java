package com.example.data;

import com.example.model.LoginTestData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class TestDataReader {

    public static List<LoginTestData> getLoginNegativeData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File("src/test/resources/login-negative-test-data.json"),
                new TypeReference<>() {
                }
        );
    }

}
