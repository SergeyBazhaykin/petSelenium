package org.raido.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.raido.utils.DataReader;
import org.raido.utils.WaitUtils;

import java.util.Map;

public class RegisterPage {
    private final WebDriver driver;
    private final int waitTimeInSeconds;
    private final int shortWaitTimeInSeconds;

    private final By inputEmailLocator = By.xpath("//input[@name='email']");
    private final By inputPasswordLocator = By.xpath("//input[@name='password']");
    private final By emailEnterLocator = By.xpath("//button[.//text()='Далее']");
    private final By errorMessageLocator = By.cssSelector("p.error-message");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.waitTimeInSeconds = 5;
        this.shortWaitTimeInSeconds = 1;
    }

    @Step("Ввод email: {email}")
    public void insertEmail(String email) {
        WaitUtils.waitForElementVisibility(driver, inputEmailLocator, waitTimeInSeconds);

        driver.findElement(inputEmailLocator).sendKeys(email);

        driver.findElement(inputEmailLocator).sendKeys(Keys.TAB);
    }

    @Step("Попытка отправки email: {email}")
    public void sendEmail(String email) {
        WaitUtils.waitForElementVisibility(driver, inputEmailLocator, waitTimeInSeconds);

        driver.findElement(inputEmailLocator).sendKeys(email);

        driver.findElement(emailEnterLocator).click();
    }

    @Step("Получение текущего адреса страницы")
    public String getCurrentUrl() {
        WaitUtils.waitForPageLoad(driver, waitTimeInSeconds);

        return driver.getCurrentUrl();
    }

    @Step("Проверка вывода сообщения об ошибке")
    public boolean isErrorMessageDisplayed(String errorLocator) {
        WaitUtils.waitForElementVisibility(driver, errorMessageLocator, shortWaitTimeInSeconds);

        Map<String, String> resultData = DataReader.getObjectByLocator("error_messages.json", errorLocator);
        String errorMessage = resultData.get("errorMessage");

        return driver.findElement(errorMessageLocator).getText().equals(errorMessage);
    }
}
