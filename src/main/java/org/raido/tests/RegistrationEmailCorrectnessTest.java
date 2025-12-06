package org.raido.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.raido.BaseTest;
import org.raido.pages.LoginPage;
import org.raido.pages.MainPage;
import org.raido.pages.RegisterPage;
import org.raido.utils.TestDataProviders;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.TimeoutException;

import java.util.Map;

public class RegistrationEmailCorrectnessTest extends BaseTest {
    @Story("Проверка cообщения о некорректном email при регистрации")
    @Owner("SergeyB")
    @Description("Попытка регистрации с тестовым email")
    @Test(description = "Проверка cообщения о некорректном email при регистрации",
            dataProvider = "emails", dataProviderClass = TestDataProviders.class)
    public void testLoginScenarios(Map<String, String> testCase) {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        String errorLocator = testCase.get("errorLocator");
        String email = testCase.get("email");

        mainPage.clickLoginButtonOnMainPage();
        loginPage.clickRegisterButtonOnLoginPage();

        registerPage.insertEmail(email);
        try {
            softAssert.assertTrue(registerPage.isErrorMessageDisplayed(errorLocator),
                    "Нет индикации неверного email на странице регистрации для сценария: "
                            + testCase.get("description"));
        } catch (TimeoutException ignored) {
            softAssert.fail("Нет сообщения о неверном email для сценария: "
                    + testCase.get("description"));
        }
        softAssert.assertAll();
    }
}
