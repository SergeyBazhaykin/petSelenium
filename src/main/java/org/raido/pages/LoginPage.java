package org.raido.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.raido.utils.DataReader;
import org.raido.utils.WaitUtils;

import java.util.Map;

public class LoginPage {
    private final WebDriver driver;
    private final int waitTimeInSeconds;

    private final By inputEmailLocator = By.xpath("//input[@id='E-mail']");
    private final By inputPasswordLocator = By.xpath("//input[@name='password']");
    private final By buttonEnterLocator = By.xpath("//button[.//text()='Продолжить']");
    private final By errorMessageLocator = By.xpath("//span[@class='massage-error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitTimeInSeconds = 5;
    }

    @Step("Авторизация с логином: {email} и паролем: {password}")
    public void login(String email, String password) {
        WaitUtils.waitForElementVisibility(driver, inputEmailLocator, waitTimeInSeconds);

        driver.findElement(inputEmailLocator).sendKeys(email);
        driver.findElement(inputPasswordLocator).sendKeys(password);

        driver.findElement(buttonEnterLocator).click();
    }

    @Step("Получение текущего адреса страницы")
    public String getCurrentUrl() {
        WaitUtils.waitForPageLoad(driver, waitTimeInSeconds);
        return driver.getCurrentUrl();
    }

    @Step("Проверка вывода сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        WaitUtils.waitForElementVisibility(driver, errorMessageLocator, waitTimeInSeconds);

        Map<String, String> resultData = DataReader.getObjectByLocator("error_messages.json", "loginPageResult");
        String errorMessage = resultData.get("errorMessage");

        return driver.findElement(errorMessageLocator).getText().equals(errorMessage);
    }
}
