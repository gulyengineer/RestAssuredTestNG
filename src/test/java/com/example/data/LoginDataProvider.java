package com.example.data;

import com.example.model.LoginTestData;
import org.testng.annotations.DataProvider;

import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData() throws Exception {

        List<LoginTestData> data =
                TestDataReader.getLoginNegativeData();

        return data.stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
    }
}
