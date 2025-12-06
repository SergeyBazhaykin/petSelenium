package org.raido.tests;

import org.raido.BaseTest;
import org.raido.pages.LoginPage;
import org.raido.pages.MainPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Story;
import io.qameta.allure.Owner;
import io.qameta.allure.Description;
import java.util.Map;
import org.raido.utils.TestDataProviders;

public class LoginTest extends BaseTest {
    @Story("Проверка авторизации на сайте")
    @Owner("SergeyB")
    @Description("Попытка входа на сайт с тестовыми данными")
    @Test(description = "Проверка авторизации на сайте",
            dataProvider = "loginData", dataProviderClass = TestDataProviders.class)
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
                "После логина URL не соответствует ожидаемому для сценария: "
                        + testCase.get("description"));

        if (!isSuccess) {
            softAssert.assertTrue(loginPage.isErrorMessageDisplayed(),
                    "Нет индикации ошибки на странице логина для сценария: "
                            + testCase.get("description"));
        }

        softAssert.assertAll();
    }
}
