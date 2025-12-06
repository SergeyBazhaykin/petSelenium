package org.raido.utils;

import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class TestDataProviders {
    @DataProvider(name = "loginData")
    public Object[][] getLoginDataCorrect() {
        List<Map<String, String>> data = DataReader.readJsonData("login_data.json");
        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }


    @DataProvider(name = "errorMessages")
    public Object[][] getErrorMessages() {
        List<Map<String, String>> data = DataReader.readJsonData("error_messages.json");
        Object[][] result = new Object[1][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }

    @DataProvider(name = "emails")
    public Object[][] getEmails() {
        List<Map<String, String>> data = DataReader.readJsonData("emails.json");
        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }
}
