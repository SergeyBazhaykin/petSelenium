package org.raido;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuccessfulLoginTest extends BaseTest {
    private static final String testEmail = "1234Aa@askd.com";
    private static final String testPassword = "1234Aa!!";

    @Test(description = "Проверка успешной авторизации на сайте")
    public static void testSuccessfulLogin() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButtonOnMainPage();

        mainPage.login(testEmail, testPassword);

        String actualUrl = mainPage.getCurrentUrl();
        String expectedUrl = "https://test-mrn.astondevs.ru/";

        softAssert.assertEquals(actualUrl, expectedUrl, "После входа URL должен вести на главную.");

        softAssert.assertAll();
    }
}
