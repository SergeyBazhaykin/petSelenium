package org.raido.pages;

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

    public void clickLoginButtonOnMainPage() {
        WaitUtils.waitForElementClickable(driver, buttonLKLocator, waitTimeInSeconds);
        driver.findElement(buttonLKLocator).click();
    }

    public void clickExchangeRatesButtonOnMainPage() {
        WaitUtils.waitForElementClickable(driver, buttonExchangeRatesLocator, waitTimeInSeconds);
        driver.findElement(buttonExchangeRatesLocator).click();
    }

    public String getCurrentUrl() {
        WaitUtils.waitForPageLoad(driver, waitTimeInSeconds);
        return driver.getCurrentUrl();
    }
}
