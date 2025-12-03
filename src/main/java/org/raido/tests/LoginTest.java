package org.raido.tests;

import org.raido.BaseTest;
import org.raido.pages.LoginPage;
import org.raido.pages.MainPage;
import org.raido.utils.DataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        List<Map<String, String>> data = DataReader.readJsonData("login_data.json");
        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }

    @Test(description = "Проверка успешной авторизации на сайте", dataProvider = "loginData")
    public void testLoginScenarios(Map<String, String> testCase) {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String testEmail = testCase.get("email");
        String testPassword = testCase.get("password");
        String expectedUrl = testCase.get("expectedUrl");
        boolean isSuccess = Boolean.parseBoolean(testCase.get("isSuccess"));

        mainPage.clickLoginButtonOnMainPage();

        loginPage.login(testEmail, testPassword);

        String actualUrl = loginPage.getCurrentUrl();

        softAssert.assertEquals(actualUrl, expectedUrl,
                "После логина URL не соответствует ожидаемому для сценария: " + testCase.get("description"));

        if (!isSuccess) {
            //softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), "Нет индикации ошибки на странице логина.");
        }

        softAssert.assertAll();
    }
}
