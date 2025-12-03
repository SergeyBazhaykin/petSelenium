package org.raido.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.raido.utils.WaitUtils;

public class MainPage {
    private final WebDriver driver;
    private final int waitTimeInSeconds;

    private final By buttonLKLocator = By.xpath("//a[.//text()='Войти']");
    private final By buttonExchangeRatesLocator = By.xpath("//a[.//text()='Курсы валют']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.waitTimeInSeconds = 5;
    }

    @Step("Переход на страницу авторизации")
    public void clickLoginButtonOnMainPage() {
        WaitUtils.waitForElementClickable(driver, buttonLKLocator, waitTimeInSeconds);
        driver.findElement(buttonLKLocator).click();
    }

    @Step("Переход на страницу курсов валют")
    public void clickExchangeRatesButtonOnMainPage() {
        WaitUtils.waitForElementClickable(driver, buttonExchangeRatesLocator, waitTimeInSeconds);
        driver.findElement(buttonExchangeRatesLocator).click();
    }

    @Step("Получение текущего адреса страницы")
    public String getCurrentUrl() {
        WaitUtils.waitForPageLoad(driver, waitTimeInSeconds);
        return driver.getCurrentUrl();
    }
}
